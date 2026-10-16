package ticketquikit;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Applicant class.
 * 
 * @author Makayla Irwin
 * @version 2026.10.14
 */
public class ApplicantTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Applicant evil;
    private Applicant first;
    private Applicant second;

    // ~ Public Methods ........................................................
    /**
     * Resets the static ID counter and creates two applicants before each
     * test so tests don't depend on each other.
     */
    public void setUp()
    {
        Applicant.globalID = 0;
        evil = new Applicant(null, null, 1);
        Applicant.globalID = 0;
        first = new Applicant("Ada", "Lovelace", 1);
        second = new Applicant("Alan", "Turing", 5);
    }


    // ----------------------------------------------------------
    /**
     * Tests the name getters.
     */
    public void testGetNames()
    {
        assertEquals("Ada", first.getFirstName());
        assertEquals("Lovelace", first.getLastName());
        assertEquals("Alan", second.getFirstName());
        assertEquals("Turing", second.getLastName());
        assertEquals(null, evil.getFirstName());
        assertEquals(null, evil.getLastName());
    }

    // ----------------------------------------------------------
    /**
     * Tests the clearance getter.
     */
    public void testGetClearance()
    {
        assertEquals(1, first.getClearance());
        assertEquals(5, second.getClearance());
    }


    // ----------------------------------------------------------
    /**
     * Tests that IDs start at 0 and increment by 1.
     */
    public void testIdsIncrement()
    {
        assertEquals(0, first.getID());
        assertEquals(1, second.getID());

        Applicant third = new Applicant("Grace", "Hopper", 1);
        assertEquals(2, third.getID());
    }


    // ----------------------------------------------------------
    /**
     * Tests that globalID is always the next ID to be assigned.
     */
    public void testGlobalId()
    {
        assertEquals(2, Applicant.globalID);
        new Applicant("Grace", "Hopper", 1);
        assertEquals(3, Applicant.globalID);
    }


    // ----------------------------------------------------------
    /**
     * Tests that the counter starts over when globalID is reset.
     */
    public void testGlobalIdReset()
    {
        Applicant.globalID = 0;
        Applicant fresh = new Applicant("Linus", "Torvalds", 1);
        assertEquals(0, fresh.getID());
        assertEquals(1, Applicant.globalID);
    }


    // ----------------------------------------------------------
    /**
     * Tests equals when comparing an applicant to itself.
     */
    public void testEqualsSameObject()
    {
        assertTrue(first.equals(first));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals against null.
     */
    public void testEqualsNull()
    {
        assertFalse(first.equals(null));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals against an object of a different class.
     */
    public void testEqualsDifferentClass()
    {
        assertFalse(first.equals("Ada Lovelace"));
        assertFalse(first.equals(Integer.valueOf(0)));
    }


    // ----------------------------------------------------------
    /**
     * Tests that applicants with different IDs are not equal.
     */
    public void testEqualsDifferentId()
    {
        assertFalse(first.equals(second));
        assertFalse(second.equals(first));
    }


    // ----------------------------------------------------------
    /**
     * Tests that two applicants with identical names are still different
     * because their IDs are different.
     */
    public void testEqualsSameNameDifferentId()
    {
        Applicant twin = new Applicant("Ada", "Lovelace", 1);
        assertFalse(first.equals(twin));
        assertFalse(twin.equals(first));
    }


    // ----------------------------------------------------------
    /**
     * Tests that two applicants with the same ID are equal even if their
     * names differ. Resetting globalID forces a repeated ID.
     */
    public void testEqualsSameIdDifferentName()
    {
        Applicant.globalID = 0;
        Applicant sameId = new Applicant("Someone", "Else", 1);
        assertEquals(first.getID(), sameId.getID());
        assertTrue(first.equals(sameId));
        assertTrue(sameId.equals(first));
        assertEquals(first.hashCode(), sameId.hashCode());
    }


    // ----------------------------------------------------------
    /**
     * Tests that hashCode is consistent across calls and differs for
     * different applicants.
     */
    public void testHashCode()
    {
        assertEquals(first.hashCode(), first.hashCode());
        assertEquals(second.hashCode(), second.hashCode());
        assertTrue(first.hashCode() != second.hashCode());
    }


    // ----------------------------------------------------------
    /**
     * Tests toString.
     */
    public void testToString()
    {
        assertEquals("Ada Lovelace (ID: 0)", first.toString());
        assertEquals("Alan Turing (ID: 1)", second.toString());
    }
}
