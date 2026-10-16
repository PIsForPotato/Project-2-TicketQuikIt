package ticketquikit;

// -------------------------------------------------------------------------
/**
 * Represents a person applying for tickets. Each Applicant is automatically
 * assigned a unique, incrementing ID when created.
 * 
 * @author Makayla Irwin
 * @version 2026.10.14
 */
public class Applicant
{
    // ~ Fields ................................................................
    /**
     * The ID that will be given to the next Applicant created. Starts at 0, so
     * the most recently assigned ID is always globalID - 1.
     */
    public static int globalID = 0;

    private final int id;
    private int clearance;
    private String firstName;
    private String lastName;

    // ~ Constructors ..........................................................
    /**
     * Create a new Applicant object and assign it the next available ID.
     * 
     * @param firstName
     *            the applicant's first name
     * @param lastName
     *            the applicant's last name
     * @param clearance
     *            the applciant's clearance ;eve;
     */
    public Applicant(String firstName, String lastName, int clearance)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clearance = clearance;
        this.id = globalID;
        globalID++;
    }


    // ~ Public Methods ........................................................
    /**
     * Get the applicant's ID.
     * 
     * @return the unique ID
     */
    public int getID()
    {
        return id;
    }


    // ----------------------------------------------------------
    /**
     * Get the applicant's first name.
     * 
     * @return first name
     */
    public String getFirstName()
    {
        return firstName;
    }


    // ----------------------------------------------------------
    /**
     * Get the applicant's last name.
     * 
     * @return last name
     */
    public String getLastName()
    {
        return lastName;
    }


    // ----------------------------------------------------------
    /**
     * Get the applicant's clearance.
     * 
     * @return clearance
     */
    public int getClearance()
    {
        return clearance;
    }


    // ----------------------------------------------------------
    /**
     * Two Applicants are equal if they have the same ID, since IDs are unique.
     * 
     * @param obj
     *            the object to compare to
     * @return true if obj is an Applicant with the same ID
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Applicant other = (Applicant)obj;
        return id == other.id;
    }


    // ----------------------------------------------------------
    /**
     * Hash code consistent with equals(), based on the ID.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode()
    {
        return Integer.hashCode(id);
    }


    // ----------------------------------------------------------
    /**
     * Describes the applicant.
     * 
     * @return a string with the applicant's name and ID
     */
    @Override
    public String toString()
    {
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
}
