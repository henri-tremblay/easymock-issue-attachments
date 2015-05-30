package sample;

import org.junit.Test;
import org.easymock.*;
import static org.easymock.EasyMock.*;

interface Collaborator
{
   void doSomething(int value);
}

class ObscuringUnit
{
   private final Collaborator collaborator;

   public ObscuringUnit(Collaborator collaborator)
   {
      this.collaborator = collaborator;
   }

   public void run()
   {
      try
      {
         collaborator.doSomething(42);
         collaborator.doSomething(17);
      }
      catch (Throwable ex)
      {
         ex.printStackTrace();
      }
   }
}

public class ObscuringUnitTest
{
   private IMocksControl control = EasyMock.createControl();

   @Test
   public void test()
   {
      final Collaborator collaborator = control.createMock(Collaborator.class);

      collaborator.doSomething(eq(42));

      ObscuringUnit unit = new ObscuringUnit(collaborator);

      control.replay();

      unit.run();

      control.verify();
   }
}
