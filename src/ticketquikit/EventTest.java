package ticketquikit;

import student.TestCase;

/**
 * Tests the methods in the Event class.
 *
 * @lehiem
 * @version 2026-10-16
 */
public class EventTest extends TestCase
{
    private Event event;
    private Applicant applicant1;
    private Applicant applicant2;
    private Applicant applicant3;

    /**
     * Sets up the objects used by the test methods.
     */
    public void setUp()
    {
        Applicant.globalID = 0;
        event = new Event("Concert", 2);
        applicant1 = new Applicant("John", "Smith", 1);
        applicant2 = new Applicant("Jane", "Doe", 1);
        applicant3 = new Applicant("Alex", "Brown", 1);
    }


    /**
     * Tests the constructor and getter methods.
     */
    public void testConstructorAndGetters()
    {
        assertEquals("Concert", event.getEventTitle());
        assertEquals(2, event.getEventCapacity());

        Event anotherEvent = new Event("Festival", 5);
        assertEquals("Festival", anotherEvent.getEventTitle());
        assertEquals(5, anotherEvent.getEventCapacity());
    }


    /**
     * Tests adding an applicant to the event.
     */
    public void testAddApplicant()
    {
        assertTrue(event.addApplicant(applicant1));
        assertTrue(event.addApplicant(applicant1));
        assertTrue(event.addApplicant(applicant2));

        assertEquals(2, event.countApplicant(0));
        assertEquals(1, event.countApplicant(1));
    }


    /**
     * Tests getTicketWinners when the number of applicants
     * is greater than the event capacity.
     */
    public void testGetTicketWinnersAtCapacity()
    {
        event.addApplicant(applicant1);
        event.addApplicant(applicant2);
        event.addApplicant(applicant3);

        Applicant[] winners = event.getTicketWinners();

        assertEquals(2, winners.length);
        assertSame(applicant1, winners[0]);
        assertSame(applicant2, winners[1]);

        // Getting winners should not remove applicants.
        assertEquals(1, event.countApplicant(0));
        assertEquals(1, event.countApplicant(1));
        assertEquals(1, event.countApplicant(2));
    }


    /**
     * Tests getTicketWinners when fewer applicants than the
     * event capacity have entered.
     */
    public void testGetTicketWinnersBelowCapacity()
    {
        event.addApplicant(applicant1);

        Applicant[] winners = event.getTicketWinners();

        assertEquals(1, winners.length);
        assertSame(applicant1, winners[0]);
    }


    /**
     * Tests getTicketWinners when there are no applicants.
     */
    public void testGetTicketWinnersEmpty()
    {
        Applicant[] winners = event.getTicketWinners();

        assertEquals(0, winners.length);
    }


    /**
     * Tests deleting winners when the number of applicants
     * is greater than the event capacity.
     */
    public void testDeleteTicketWinnersAtCapacity()
    {
        event.addApplicant(applicant1);
        event.addApplicant(applicant2);
        event.addApplicant(applicant3);

        assertTrue(event.deleteTicketWinners());

        // The first two applicants should have been removed.
        assertEquals(0, event.countApplicant(0));
        assertEquals(0, event.countApplicant(1));

        // The third applicant should remain.
        assertEquals(1, event.countApplicant(2));

        Applicant[] winners = event.getTicketWinners();
        assertEquals(1, winners.length);
        assertSame(applicant3, winners[0]);
    }


    /**
     * Tests deleting winners when fewer applicants than the
     * event capacity have entered.
     */
    public void testDeleteTicketWinnersBelowCapacity()
    {
        event.addApplicant(applicant1);

        assertTrue(event.deleteTicketWinners());
        assertEquals(0, event.countApplicant(101));
        assertTrue(event.getTicketWinners().length == 0);
    }


    /**
     * Tests deleting winners when the applicant list is empty.
     */
    public void testDeleteTicketWinnersEmpty()
    {
        assertFalse(event.deleteTicketWinners());
    }


    /**
     * Tests counting applicants by ID.
     */
    public void testCountApplicant()
    {
        event.addApplicant(applicant1);
        event.addApplicant(applicant2);
        event.addApplicant(applicant3);

        assertEquals(1, event.countApplicant(0));
        assertEquals(1, event.countApplicant(1));
        assertEquals(1, event.countApplicant(2));

        // An ID that does not exist should return zero.
        assertEquals(0, event.countApplicant(999));
    }


    /**
     * Tests counting duplicate applicant IDs.
     */
    public void testCountDuplicateApplicantIDs()
    {
        Applicant.globalID = 0;
        Applicant duplicate = new Applicant("John", "Smith", 1);

        event.addApplicant(applicant1);
        event.addApplicant(duplicate);
        event.addApplicant(applicant2);

        assertEquals(2, event.countApplicant(0));
    }


    /**
     * Tests equals when comparing an event with itself.
     */
    public void testEqualsSameObject()
    {
        assertTrue(event.equals(event));
    }


    /**
     * Tests equals when two events have the same title.
     */
    public void testEqualsSameTitle()
    {
        Event anotherEvent = new Event("Concert", 10);

        assertTrue(event.equals(anotherEvent));
        assertTrue(anotherEvent.equals(event));
    }


    /**
     * Tests equals when two events have different titles.
     */
    public void testEqualsDifferentTitle()
    {
        Event anotherEvent = new Event("Festival", 2);

        assertFalse(event.equals(anotherEvent));
    }


    /**
     * Tests equals when comparing an event with null.
     */
    public void testEqualsNull()
    {
        assertFalse(event.equals(null));
    }


    /**
     * Tests equals when comparing an event with another class.
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEqualsDifferentClass()
    {
        assertFalse(event.equals("Concert"));
    }


    /**
     * Tests hashCode for events with the same title.
     */
    public void testHashCodeSameTitle()
    {
        Event anotherEvent = new Event("Concert", 10);

        assertEquals(event.hashCode(), anotherEvent.hashCode());
    }


    /**
     * Tests hashCode for events with different titles.
     */
    public void testHashCodeDifferentTitle()
    {
        Event anotherEvent = new Event("Festival", 2);

        assertFalse(event.hashCode() == anotherEvent.hashCode());
    }
}