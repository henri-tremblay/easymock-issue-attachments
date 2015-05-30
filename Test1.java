

import junit.framework.TestCase;

import org.easymock.EasyMock;

public class Test1 extends TestCase {
	private I1 mock;

	protected void setUp() throws Exception {
		super.setUp();
		
		mock = EasyMock.createMock(I1.class);
		EasyMock.expect(mock.get(EasyMock.eq(1), EasyMock.aryEq(new String[]{"a", "b"}))).andReturn(2).anyTimes();
		EasyMock.expect(mock.get(EasyMock.eq(1), EasyMock.eq("x"))).andReturn(3).anyTimes();
		
		EasyMock.expect(mock.get(EasyMock.eq(1), EasyMock.aryEq(new int[]{1, 2}))).andReturn(2).anyTimes();
		EasyMock.expect(mock.get(EasyMock.eq(1), EasyMock.eq(3))).andReturn(3).anyTimes();
		
		EasyMock.replay(mock);
	}

	public void test1() {
		assertEquals( 2, mock.get(1,  new String[] {"a", "b"} ));
		assertEquals( 2, mock.get(1,  new int[] {1, 2} ));
	}
	
	public void test2() {
		assertEquals( 2, mock.get(1,  "a", "b" ));
		assertEquals( 2, mock.get(1,  1, 2 ));
	}

	public void test3() {
		assertEquals( 3, mock.get(1,  "x" ));
		assertEquals( 3, mock.get(1,  3 ));
	}

	public void test4() {
		assertEquals( 3, mock.get(1,  new String[] {"x"} ));
		assertEquals( 3, mock.get(1,  new int[] {3} ));
	}

	public interface I1 {
		public int get(int i, String... s);
		public int get(int i, int... is);
	}
}
