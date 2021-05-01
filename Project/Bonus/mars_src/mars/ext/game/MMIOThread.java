package mars.ext.game;

import java.util.Vector;
import mars.util.SystemIO;

/**
 * 
 * @author jasonwangm
 * 
 */
public class MMIOThread extends Thread {
	private MMIOInput mmioInput;
	private Vector events;
	boolean isFinish;

	public MMIOThread(MMIOInput mmioInput) {
		super();
		this.mmioInput = mmioInput;
		this.events = new Vector();
		this.isFinish = false;
	}

	public synchronized void stopProcess() {
		this.isFinish = true;
	}

	public synchronized Object[] getEvent() {
		Object[] event = null;
		if (isFinish || events.size() <= 0) {
			return null;
		}
		event = new Object[2];
		event[0] = events.remove(0);
		if (((Integer) event[0]).intValue() == 1)
			event[1] = events.remove(0);

		return event;
	}

	public synchronized void add(int action, Character e) {
		try {
			events.addElement(new Integer(action));
			if (e != null)
				events.addElement(e);
			notify();

		} catch (Exception ex) {
			SystemIO.printString("\nMMIO error: problems in processing input keys!\n");
		}

	}

	public void run() {
		try {
			Object[] event = null;
			while (!isFinish) {
				synchronized (this) {
					event = getEvent();
					if (event == null) {
						wait(100); // even if no events, wake up periodically to
									// check whether isFinish is true
						// event = getEvent(); // get the event immediately while
											// holding the lock
					}
				}

				if (event != null)
					switch (((Integer) event[0]).intValue()) {
					case 1:
						mmioInput.writeInput(((Character) event[1]).charValue());
						break;
					case 2:
						mmioInput.clearInput();
						break;
					}
			}

		} catch (Exception e) {
			SystemIO.printString("\nMMIO error: problems in processing input keys!\n");

		}

	}
}
