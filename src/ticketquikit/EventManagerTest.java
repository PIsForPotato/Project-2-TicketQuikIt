package ticketquikit;

import java.util.ArrayList;
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
    private ArrayList<Event> eventList = new ArrayList<Event>();
    

    // ----------------------------------------------------------
    /**
     * Sets up the test.
     */
    public void setUp()
    {
        eventList.add(new Event("Event 1", 1, 1));
        eventList.add(new Event("Event 2", 5, 1));
        eventList.add(new Event("Event 3", 10, 3));
        manager = new EventManager(eventList);
        emptyManager = new EventManager(new ArrayList<Event>());
    }
    
    // ----------------------------------------------------------
    /**
     * Tests getEventList
     */
    public void testGetEventList()
    {
        for(int i=0; i < manager.getEventList().size(); i++) {
            assertEquals(eventList.get(i), manager.getEventList().get(i));
        }
        
        assertEquals(0, emptyManager.getEventList().size());
    }
    
    
    // ----------------------------------------------------------
    /**
     * Tests addEvent
     */
    public void testAddEvent()
    {
        assertTrue(emptyManager.addEvent("ABC", 1, 2));
        assertFalse(emptyManager.addEvent(null, 1, 2));
        assertFalse(emptyManager.addEvent("ABC", 0, 2));
        assertFalse(emptyManager.addEvent("ABC", 1, 0));
    }

    
    // ----------------------------------------------------------
    /**
     * Tests findEvent
     */
    public void testFindEvent() {
        assertEquals(eventList.get(0), manager.findEvent("Event 1"));
        assertEquals(eventList.get(1), manager.findEvent("Event 2"));
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
        eventList.get(0).addApplicant(applicant3);
        eventList.get(1).addApplicant(applicant3);
        
        assertEquals(2, manager.checkApplicantsEvent(2).size());
        assertEquals(0, manager.checkApplicantsEvent(3).size());

    }
}
