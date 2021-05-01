package mars.ext.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import mars.Globals;
import mars.mips.hardware.AccessNotice;
import mars.mips.hardware.Coprocessor0;
import mars.mips.hardware.Memory;
import mars.mips.hardware.MemoryAccessNotice;
import mars.simulator.Exceptions;
import mars.util.SystemIO;

/**
 * MMIOInput is modified from "mars.tools.KeyboardAndDisplaySimulator.java". 
 * 
 * @author jasonwangm
 * 
 */
public class MMIOInput implements KeyListener, Observer {
	public static int RECEIVER_CONTROL; // keyboard Ready in low-order bit
	public static int RECEIVER_DATA; // keyboard character in low-order byte
	private MMIOThread mmioThread;
	private boolean noRepeatedKeyEvents = true; // no repeated keyPress events
												// of
	// key-heldDown are processed after the
	// second (repeated) one, then
	// keyRelease event is processed
	private int keyPressNum = 0;
	private char lastKeyChar = '\0';
	private boolean acceptClearInput = true;
	

	// Set the MMIO addresses. Prior to MARS 3.7 these were final because
	// MIPS address space was final as well. Now we will get MMIO base address
	// each time to reflect possible change in memory configuration. DPS
	// 6-Aug-09
	public MMIOInput(boolean noRepeatedKeyEvents) {

		// 0xffff0000; keyboard Ready in low-order bit;
		RECEIVER_CONTROL = Memory.memoryMapBaseAddress; 
		// 0xffff004; keyboard character in low-order byte;
		RECEIVER_DATA = Memory.memoryMapBaseAddress + 4; 
		this.noRepeatedKeyEvents = noRepeatedKeyEvents;
		// Create memory updating thread that must be different from memory observer
		// thread to avoid the deadlock on MIPS-Memory lock.
		mmioThread = new MMIOThread(this); 
		mmioThread.start();
		addAsObserver();
	}

	/**
	 * destroy any used resources.
	 */
	public void destroy() {
		Globals.memory.deleteObserver(this);
		mmioThread.stopProcess();
	}

	/**
	 * Registers us as an Observer over the static data segment (starting
	 * address 0x10010000) only.
	 * 
	 * When user enters keystroke, set RECEIVER_CONTROL and RECEIVER_DATA using
	 * the action listener. When user loads word (lw) from RECEIVER_DATA (we are
	 * notified of the read), then clear RECEIVER_CONTROL.
	 */
	void addAsObserver() {
		try {
			// We want to be an observer only of MIPS reads from RECEIVER_DATA
			Globals.memory.addObserver(this, RECEIVER_DATA, RECEIVER_DATA);
		} catch (Exception aee) {
			SystemIO.printString("\nMMIO error: adding observer of incorrect MMIO address!\n");
		}
	}

	/**
	 * We get the notification of access to MIPS memory or registers. Here, it
	 * corresponds to the keystoke.
	 * 
	 * Default implementation of method required by Observer interface. This
	 * method will filter out notices originating from the MARS GUI or from
	 * direct user editing of memory or register displays. Only notices arising
	 * from MIPS program access are allowed in.
	 * 
	 * @param memory
	 *            the attached memory
	 * @param arg
	 *            information provided by memory in MemoryAccessNotice object
	 */
	public void update(Observable memory, Object arg) {

		AccessNotice accessNotice = (AccessNotice) arg;
		MemoryAccessNotice notice = (MemoryAccessNotice) accessNotice;
		// If MIPS program has just read (loaded) the receiver (keyboard) data
		// register, then clear the Ready bit to indicate there is no longer a
		// keystroke available.
		// If Ready bit was initially clear, they'll get the old keystroke --
		// serves 'em right for not checking!
		if (notice.accessIsFromMIPS() && notice.getAddress() == RECEIVER_DATA
				&& notice.getAccessType() == AccessNotice.READ) {
			synchronized (this) {
				if (!noRepeatedKeyEvents || acceptClearInput) { 
					clearInput();
				}
			}
		}
	}

	// Update the MMIO Control register memory cell.
	private void updateMMIOControl(int addr, int intValue) {
		updateMMIOControlAndData(addr, intValue, 0, 0, true);
	}

	// Update the MMIO Control and Data register pair -- 2 memory cells.
	private void updateMMIOControlAndData(int controlAddr, int controlValue,
			int dataAddr, int dataValue) {
		updateMMIOControlAndData(controlAddr, controlValue, dataAddr,
				dataValue, false);
	}

	/**
	 * Update the MMIO Control and optionally the Data register as well
	 * 
	 * controlOnly: TRUE means update only the MMIO Control register; FALSE
	 * means update both Control and Data.
	 */
	private void updateMMIOControlAndData(int controlAddr, int controlValue,
			int dataAddr, int dataValue, boolean controlOnly) {
		synchronized (Globals.memoryAndRegistersLock) {
			try {
				Globals.memory.setRawWord(controlAddr, controlValue);
				if (noRepeatedKeyEvents && (controlValue & 1) == 1) // set input
					synchronized (this) {
						if (keyPressNum >= 2)
							acceptClearInput = false;
					}
				if (!controlOnly)
					Globals.memory.setRawWord(dataAddr, dataValue);
			} catch (Exception aee) {
				SystemIO.printString("\nMMIO error: updating data of incorrect MMIO address!\n");
			}
		}
		// HERE'S A HACK!!
		// Want to immediately display the updated memory value in MARS,
		// but that code was not written for event-driven update (e.g.
		// Observer).
		// It was written to poll the memory cells for their values.
		// So we force it to do so.
		if (Globals.getGui() != null
				&& Globals.getGui().getMainPane().getExecutePane()
						.getTextSegmentWindow().getCodeHighlighting()) {
			Globals.getGui().getMainPane().getExecutePane()
					.getDataSegmentWindow().updateValues();
		}
	}

	// ///////////////////////////////////////////////////////////////////
	// Return value of the given MMIO control register after ready (low order)
	// bit set (to 1).
	// Have to preserve the value of Interrupt Enable bit (bit 1)
	private boolean isReadyBitSet(int mmioControlRegister) {
		try {
			return (Globals.memory.get(mmioControlRegister,
					Memory.WORD_LENGTH_BYTES) & 1) == 1;
		} catch (Exception aee) {
			SystemIO.printString("\nMMIO error: accessing incorrect MMIO address!\n");

		}
		return false; // to satisfy the compiler -- this will never happen.
	}

	// ///////////////////////////////////////////////////////////////////
	// Return value of the given MMIO control register after ready (low order)
	// bit set (to 1).
	// Have to preserve the value of Interrupt Enable bit (bit 1)
	private int readyBitSet(int mmioControlRegister) {
		try {
			return Globals.memory.get(mmioControlRegister,
					Memory.WORD_LENGTH_BYTES) | 1;
		} catch (Exception aee) {
			SystemIO.printString("\nMMIO error: accessing incorrect MMIO address!\n");

		}
		return 1; // to satisfy the compiler -- this will never happen.
	}

	// ///////////////////////////////////////////////////////////////////
	// Return value of the given MMIO control register after ready (low order)
	// bit cleared (to 0).
	// Have to preserve the value of Interrupt Enable bit (bit 1). Bits 2 and
	// higher don't matter.
	private int readyBitCleared(int mmioControlRegister) {
		try {
			return Globals.memory.get(mmioControlRegister,
					Memory.WORD_LENGTH_BYTES) & 2;
		} catch (Exception aee) {
			SystemIO.printString("\nMMIO error: accessing incorrect MMIO address!\n");

		}
		return 0; // to satisfy the compiler -- this will never happen.
	}

	// /////////////////////////////////////////////////////////////////////////////////
	//
	// grab keystrokes going to keyboard echo area and send them to MMIO area
	//
	public void writeInput(char key) {
		int updatedReceiverControl = readyBitSet(RECEIVER_CONTROL);
		updateMMIOControlAndData(RECEIVER_CONTROL, updatedReceiverControl,
				RECEIVER_DATA, key & 0x00000ff);
		if (updatedReceiverControl != 1
				&& (Coprocessor0.getValue(Coprocessor0.STATUS) & 2) == 0
				&& (Coprocessor0.getValue(Coprocessor0.STATUS) & 1) == 1) {
			// interrupt-enabled bit is set in both Receiver Control and in
			// Coprocessor0 Status register, and Interrupt Level Bit is 0, so
			// trigger external interrupt.
			mars.simulator.Simulator.externalInterruptingDevice = Exceptions.EXTERNAL_INTERRUPT_KEYBOARD;
		}
	}

	public void clearInput() {
		updateMMIOControl(RECEIVER_CONTROL, readyBitCleared(RECEIVER_CONTROL));
	}

	public void keyTyped(KeyEvent e) {
		// SystemIO.printString("type '" + (char) e.getKeyChar()
		// +e.getWhen()+"'\n" );
		// mmioThread.add(1, e);
	}

	public synchronized void processKeyPress(char key) {
		if (noRepeatedKeyEvents) {
			if (key != lastKeyChar){
				acceptClearInput = true;
				keyPressNum = 0;
				lastKeyChar = key;
			}

			if (keyPressNum >= 2) // holding the same key of the last key-pressing
				return;
			else
				keyPressNum++;
		}
		mmioThread.add(1, new Character(key));
	}

	public synchronized void keyPressed(KeyEvent e) {
		// SystemIO.printString("press '" + (char) e.getKeyChar()
		// + "' " + e.getWhen()+ KeyEvent.getKeyText(e.getKeyCode()) + "\n" );
		processKeyPress(e.getKeyChar());
	}

	public synchronized void processKeyRelease(char key) {

		if (noRepeatedKeyEvents && key == lastKeyChar) {
			acceptClearInput = true;
			keyPressNum = 0;
		}
	}

	public synchronized void keyReleased(KeyEvent e) {
		// SystemIO.printString("release '" + (char) e.getKeyChar()
		// + "' " + e.getWhen()+ KeyEvent.getKeyText(e.getKeyCode()) + "\n" );
		processKeyRelease((char) e.getKeyChar());
	}

}
