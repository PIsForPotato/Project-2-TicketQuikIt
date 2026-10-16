package ticketquikit;

import student.TestCase;

public class LinkedChainTest
    extends TestCase

{
    // ~ Fields ................................................................
    private LinkedChain<String> chain;


    // ~Public Methods ........................................................
    public void setUp()
    {
        chain = new LinkedChain<String>();
    }


    public void testIsEmptyAndSize()
    {
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());

        chain.add("A");
        assertFalse(chain.isEmpty());
        assertEquals(1, chain.size());

        chain.add("B");
        assertEquals(2, chain.size());
    }


    public void testAdd()
    {
        assertTrue(chain.add("A"));
        assertEquals(1, chain.size());
        assertEquals("A", chain.get(0));

        chain.add("B");
        chain.add("C");

        // add() inserts at the front.
        assertEquals("[C, B, A]", chain.toString());
        assertEquals(3, chain.size());
    }


    public void testAddNull()
    {
        try
        {
            chain.add(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }

        assertTrue(chain.isEmpty());
    }


    public void testAddToEnd()
    {
        assertTrue(chain.addToEnd("A"));
        assertTrue(chain.addToEnd("B"));
        assertTrue(chain.addToEnd("C"));

        assertEquals("[A, B, C]", chain.toString());
        assertEquals(3, chain.size());
        assertEquals("A", chain.get(0));
        assertEquals("C", chain.get(2));
    }


    public void testAddToEndEmpty()
    {
        chain.addToEnd("A");

        assertEquals("[A]", chain.toString());
        assertEquals(1, chain.size());
        assertFalse(chain.isEmpty());
    }


    public void testAddToEndNull()
    {
        try
        {
            chain.addToEnd(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }

        assertTrue(chain.isEmpty());
    }


    public void testRemove()
    {
        assertNull(chain.remove());

        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("A", chain.remove());
        assertEquals("[B, C]", chain.toString());
        assertEquals(2, chain.size());

        assertEquals("B", chain.remove());
        assertEquals("C", chain.remove());

        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertNull(chain.remove());
    }


    public void testRemoveEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        // Remove the first entry.
        assertTrue(chain.remove("A"));
        assertEquals("[B, C]", chain.toString());

        // Remove an entry from the middle.
        assertTrue(chain.remove("C"));
        assertEquals("[B]", chain.toString());

        // Remove the last remaining entry.
        assertTrue(chain.remove("B"));
        assertTrue(chain.isEmpty());

        // An entry that does not exist cannot be removed.
        assertFalse(chain.remove("D"));
        assertEquals(0, chain.size());
    }


    public void testRemoveEntryEmpty()
    {
        assertFalse(chain.remove("A"));
        assertFalse(chain.remove(null));
        assertTrue(chain.isEmpty());
    }


    public void testRemoveEntryNotFound()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertFalse(chain.remove("C"));
        assertEquals("[A, B]", chain.toString());
        assertEquals(2, chain.size());
    }


    public void testRemoveDuplicate()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");

        assertTrue(chain.remove("A"));
        assertEquals("[B, A]", chain.toString());
        assertEquals(2, chain.size());
    }


    public void testClear()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        chain.clear();

        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
        assertEquals("[]", chain.toString());
        assertNull(chain.remove());
    }


    public void testClearEmpty()
    {
        chain.clear();

        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }


    public void testGet()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }


    public void testGetInvalidIndex()
    {
        try
        {
            chain.get(0);
            fail("Expected IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }

        chain.add("A");

        try
        {
            chain.get(-1);
            fail("Expected IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }

        try
        {
            chain.get(1);
            fail("Expected IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }
    }


    public void testContains()
    {
        assertFalse(chain.contains("A"));
        assertFalse(chain.contains(null));

        chain.addToEnd("A");
        chain.addToEnd("B");

        assertTrue(chain.contains("A"));
        assertTrue(chain.contains("B"));
        assertFalse(chain.contains("C"));
        assertFalse(chain.contains(null));
    }


    public void testCount()
    {
        assertEquals(0, chain.count("A"));
        assertEquals(0, chain.count(null));

        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");
        chain.addToEnd("A");

        assertEquals(3, chain.count("A"));
        assertEquals(1, chain.count("B"));
        assertEquals(0, chain.count("C"));
    }


    public void testToArray()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        String[] values = new String[3];
        String[] result = chain.toArray(values);

        assertSame(values, result);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);
        assertEquals("C", result[2]);
    }


    public void testToArrayLargerArray()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        String[] values = new String[4];
        values[2] = "X";

        String[] result = chain.toArray(values);

        assertSame(values, result);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);

        // The method does not modify unused array positions.
        assertEquals("X", result[2]);
    }


    public void testToArrayEmpty()
    {
        String[] values = new String[2];
        String[] result = chain.toArray(values);

        assertSame(values, result);
        assertEquals(0, chain.size());
    }


    public void testToArrayNull()
    {
        try
        {
            chain.toArray(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }


    public void testToArrayTooSmall()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        try
        {
            chain.toArray(new String[1]);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }


    public void testToString()
    {
        assertEquals("[]", chain.toString());

        chain.addToEnd("A");
        assertEquals("[A]", chain.toString());

        chain.addToEnd("B");
        assertEquals("[A, B]", chain.toString());

        chain.clear();
        assertEquals("[]", chain.toString());
    }

}
