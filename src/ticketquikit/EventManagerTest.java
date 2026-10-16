package ticketquikit;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the EventManager class
 * 
 * @author Alex Zhao (zalex)
 * @version 2026.10.15
 */
public class EventManagerTest
    extends TestCase

{
    private EventManager manager;
    private EventManager emptyManager;
    private Event[] eventArray = { new Event("Event 1", 10),
        new Event("Event 2", 5), new Event("Event 3", 20) };

    // ----------------------------------------------------------
    /**
     * Sets up the test.
     */
    public void setUp()
    {
        manager = new EventManager(eventArray);
        emptyManager = new EventManager(new Event[0]);
    }

    // ----------------------------------------------------------
    /**
     * Tests getEventList
     */
    public void testGetEventList()
    {
        for(int i=0; i < manager.getEventList().length; i++) {
            assertEquals(eventArray[i], manager.getEventList()[i]);
        }
        
        assertEquals(0, emptyManager.getEventList().length);
    }
    
    // ----------------------------------------------------------
    /**
     * Tests findEvent
     */
    public void testFindEvent() {
        assertEquals(eventArray[0], manager.findEvent("Event 1"));
        assertEquals(eventArray[1], manager.findEvent("Event 2"));
        assertNull(manager.findEvent("Event 4"));
    }
    
    // ----------------------------------------------------------
    /**
     * Tests checkApplicants
     */
    @SuppressWarnings("unused")
    public void testCheckApplicantsEvent() {
        Applicant.globalID = 0;
        for (int i = 0; i < 2; i++) {
            new Applicant("John", "Doe", 1);
        }
        Applicant applicant3 = new Applicant("John", "Doe", 1);
        eventArray[0].addApplicant(applicant3);
        eventArray[1].addApplicant(applicant3);
        
        assertEquals(2, manager.checkApplicantsEvent(2).size());
        assertEquals(0, manager.checkApplicantsEvent(3).size());

    }
}
