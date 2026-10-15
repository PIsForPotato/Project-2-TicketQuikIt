package ticketquikit;

// -------------------------------------------------------------------------
/**
 * Manages an event array field with a search method that uses a LinkedChain to
 * store and return Events that an Applicant participates in.
 * 
 * @author Alex Zhao (zalex)
 * @version 2026.10.14
 */
public class EventManager
{
    // ~ Fields ................................................................
    private Event[] eventList;

    // ~ Constructors ..........................................................
    /**
     * Create a new EventManager object.
     * 
     * @param eventList
     */
    public EventManager(Event[] eventList)
    {
        this.eventList = eventList;
    }


    // Public Methods ........................................................
    /**
     * Get event list array
     * @return event array
     */
    
    public Event[] getEventList() {
        return eventList;
    }
    
    // ----------------------------------------------------------
    /**
     * Finds an event based on provided title
     * @param title
     * @return the found Event object
     */
    public Event findEvent(String title) {
        Event event;
        for (int i = 0; i < eventList.length; i++)
        {
            if (eventList[i].getEventTitle().equals(title)) {
                return eventList[i];
            }
        }
        return null;
    }
    
    
    // ----------------------------------------------------------
    /**
     * Creates and returns a linked chain of all the events in eventList where
     * an Applicant with id exists.
     * 
     * @param id
     * @return Linked chain of all events where an Applicant w/ id exists
     */
    public LinkedChain<Event> checkApplicantsEvent(int id)
    {
        LinkedChain<Event> returnLinkedChain = new LinkedChain<Event>();

        for (int i = 0; i < eventList.length; i++)
        {
            for (int j = 0; j < eventList[i].countApplicant(id); j++) {
                returnLinkedChain.add(eventList[i]);
            }
        }
        return returnLinkedChain;
    }

}
