package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;

public interface Syscall {
  String getName();
  
  void setNumber(int paramInt);
  
  int getNumber();
  
  void simulate(ProgramStatement paramProgramStatement) throws ProcessingException;
}


/* Location:              /Users/l/Desktop/COMP2611 TA 2020fall/prj/Web/2016F/project/NewMars_source_test.jar!/mars/mips/instructions/syscalls/Syscall.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */