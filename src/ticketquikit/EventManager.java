package ticketquikit;
import java.util.ArrayList;

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
    private ArrayList<Event> eventList;

    // ~ Constructors ..........................................................
    /**
     * Create a new EventManager object.
     * 
     * @param eventList
     */
    public EventManager(ArrayList<Event> eventList)
    {
        this.eventList = eventList;
    }


    // Public Methods ........................................................
    /**
     * Get event list array
     * 
     * @return event array
     */

    public ArrayList<Event> getEventList()
    {
        return eventList;
    }


    // ----------------------------------------------------------
    /**
     * Add a new event.
     * @param eventTitle
     * @param eventCapacity
     * @param security
     * @return true if successfully added
     */
    public boolean addEvent(String eventTitle, int eventCapacity, int security)
    {
        if (eventTitle==null || eventCapacity<=0 || security <=0) {
            return false;
        }
        Event newEvent = new Event(eventTitle, eventCapacity, security);
        eventList.add(newEvent);
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Finds an event based on provided title
     * 
     * @param title
     * @return the found Event object
     */
    public Event findEvent(String title)
    {
        for (int i = 0; i < eventList.size(); i++)
        {
            if (eventList.get(i).getEventTitle().equals(title))
            {
                return eventList.get(i);
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

        for (int i = 0; i < eventList.size(); i++)
        {
            for (int j = 0; j < eventList.get(i).countApplicant(id); j++)
            {
                returnLinkedChain.add(eventList.get(i));
            }
        }
        return returnLinkedChain;
    }

}
