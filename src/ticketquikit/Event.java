package ticketquikit;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents an event that applicants can enter to receive tickets. Stores the
 * event's title, ticket capacity, and list of applicants.
 */
public class Event
{
    private String eventTitle;
    private int eventCapacity;
    private LinkedChain<Applicant> applicants;

    /**
     * Constructs an Event with a title and a ticket capacity.
     *
     * @param eventTitle
     *            the title of the event
     * @param eventCapacity
     *            the number of applicants who can win tickets
     */
    public Event(String eventTitle, int eventCapacity)
    {
        this.eventTitle = eventTitle;
        this.eventCapacity = eventCapacity;

        // Creates an empty linked chain to store applicants in order.
        applicants = new LinkedChain<Applicant>();
    }


    /**
     * Returns the title of the event.
     *
     * @return the event title
     */
    public String getEventTitle()
    {
        return eventTitle;
    }


    /**
     * Returns the maximum number of ticket winners for the event.
     *
     * @return the event capacity
     */
    public int getEventCapacity()
    {
        return eventCapacity;
    }


    /**
     * Adds an applicant to the end of the applicant list.
     *
     * @param applicant
     *            the applicant being added
     * @return true if the applicant was added successfully
     */
    public boolean addApplicant(Applicant applicant)
    {
        // Adding to the end preserves the first-come, first-served order.
        return applicants.addToEnd(applicant);
    }


    /**
     * Returns an array containing the ticket winners for this event. Winners
     * are the first applicants in the linked chain, up to the event's ticket
     * capacity.
     *
     * @return an array containing the ticket winners
     */
    public Applicant[] getTicketWinners()
    {
        // Converts the entire linked chain into an array.
        Applicant[] allApplicants =
            applicants.toArray(new Applicant[applicants.size()]);

        // The number of winners cannot exceed the capacity or the
        // number of applicants who entered the event.
        int winnerCount = Math.min(eventCapacity, allApplicants.length);

        // Returns only the first winnerCount applicants.
        // The original applicant list remains unchanged.
        return Arrays.copyOf(allApplicants, winnerCount);
    }


    /**
     * Removes the first eventCapacity applicants from the applicant list.
     *
     * @return true if at least one applicant was removed; false otherwise
     */
    public boolean deleteTicketWinners()
    {
        // Returns false if there are no applicants.
        if (applicants.isEmpty())
        {
            return false;
        }

        // Finds the smaller value between eventCapacity and the
        // number of applicants currently in the chain.
        int winnerCount = Math.min(eventCapacity, applicants.size());

        // Removes the first applicant repeatedly until all winners
        // have been removed.
        for (int i = 0; i < winnerCount; i++)
        {
            applicants.remove();
        }

        return true;
    }


    /**
     * Counts how many times an applicant with the specified ID appears in the
     * applicant list.
     *
     * @param id
     *            the ID number being searched for
     * @return the number of matching applicants
     */
    public int countApplicant(int id)
    {
        int count = 0;

        // Converts the linked chain into an array so it can be traversed.
        Applicant[] allApplicants =
            applicants.toArray(new Applicant[applicants.size()]);

        // Checks every applicant in the array.
        for (Applicant applicant : allApplicants)
        {
            // Increases the count when an applicant's ID matches.
            if (applicant.getID() == id)
            {
                count++;
            }
        }

        // Returns the total number of matching IDs.
        return count;
    }


    /**
     * Checks whether two Event objects are equal. Two events are considered
     * equal if they have the same title.
     *
     * @param obj
     *            the object being compared to this event
     * @return true if both events have the same title; false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        // An object is always equal to itself.
        if (this == obj)
        {
            return true;
        }

        // Different classes or a null object cannot be equal to this event.
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        // Converts obj to Event so its title can be compared.
        Event other = (Event)obj;

        // Compares the titles, handling null titles safely.
        return Objects.equals(eventTitle, other.eventTitle);
    }


    /**
     * Generates a hash code based on the event title. Uses the same field as
     * equals() to keep the methods consistent.
     *
     * @return the hash code for this event
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(eventTitle);
    }
    
    // ----------------------------------------------------------
    /**
     * Describes the Event.
     * 
     * @return a string with the Event's title and carrying capacity
     */
    @Override
    public String toString()
    {
        return eventTitle + " (Event Capacity: " + eventCapacity + ")";
    }
}
