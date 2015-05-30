package sandbox;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class EasyMocksSandbox extends EasyMockSupport {

    private static interface Interface {
        Object method(long l);
    }

    @Test
    public void test() throws Throwable {
        Interface mock = createMock(Interface.class);

        expect(mock.method(eq(1))).andReturn(null);

        replayAll();

        mock.method(1);

        verifyAll();
    }

}
