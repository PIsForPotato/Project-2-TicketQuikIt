package ticketquikit;

import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Represents and includes the prompting and navigation for the program’s menus.
 * Includes verification and re-prompting for the various prompts.
 * 
 * @author Alex Zhao (zalex)
 * @version 2026.10.14
 */
public class Menu
{
    // ~ Fields ................................................................
    private Scanner scanner;
    private EventManager eventManager;

    // ~ Constructors ..........................................................
    /**
     * Create a new Menu object.
     * 
     * @param eventManager
     */
    public Menu(EventManager eventManager)
    {
        this.eventManager = eventManager;
        scanner = new Scanner(System.in);
    }


    // ~Private Methods ........................................................
    private void addApplicantProcess()
    {
        boolean active = true;
        System.out.println(
            "----------------------------------------------------------");
        String firstName = firstNamePrompt();
        String lastName = lastNamePrompt();
        int clearance = clearanceLevelPrompt();
        Applicant newApplicant = new Applicant(firstName, lastName, clearance);
        while (active)
        {
            Event event = addApplicantToEventPrompt();
            if (event != null)
            {
                event.addApplicant(newApplicant);
                active = redoActionPrompt();
            }
            else
            {
                active = false;
            }
        }
    }


    private void checkApplicantProcess()
    {
        boolean active = true;
        while (active)
        {
            System.out.println(
                "----------------------------------------------------------");
            int id = idPrompt();
            
            if (id != -1) {
                LinkedChain<Event> eventChain =
                    eventManager.checkApplicantsEvent(id);
                
                String chainString = eventChain.toString();
                if (chainString.equals("[]")) {
                    System.out.println("This Applicant is not in any queue!");
                }
                else {
                    System.out.println(chainString);
                }
            }
            active = redoActionPrompt();
        }
    }


    private void distributeTicketsProcess()
    {
        boolean delete = false;
        Event event = null;
        
        while (event==null) {
            System.out.println(
                "----------------------------------------------------------");
            String title = eventTitlePrompt();
            event = eventManager.findEvent(title);
    
            if (event == null)
            {
                System.out.println("Please input a valid title.");
            }
            else
            {
                Applicant[] ticketWinners = event.getTicketWinners();
                
                if (ticketWinners.length==0) {
                    System.out.println("No Winners!");
                }
                else {
                    for (int i = 0; i < ticketWinners.length; i++)
                    {
                        System.out.println(
                            "Winner #" + (i+1) + " is " + ticketWinners[i].getFirstName()
                                + " " + ticketWinners[i].getLastName()
                                + " with an ID of " + ticketWinners[i].getID());
                    }
                }

    
                delete = deleteTicketWinnersPrompt();
                if (delete)
                {
                    event.deleteTicketWinners();
                }
            }
        }
    }


    private boolean isNumeric(String input)
    {
        try
        {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }


    // ~Public Methods ........................................................
    /**
     * Place a description of your method here.
     */
    public void mainMenuPrompt()
    {
        int choice = -1;

        while (choice == -1)
        {
            System.out.println(
                "----------------------------------------------------------\n"
                    + "What do you want to do?\n" + "0 - Add Applicant\n"
                    + "1 - Check Applicant\n" + "2 - Distribute Event Tickets\n"
                    + "3 - Quit");
            System.out.print("Enter choice: ");

            try
            {
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please input a valid integer.");
                choice = -1;
            }

        }

        switch (choice)
        {
            case 0:
                addApplicantProcess();
                mainMenuPrompt();
                break;

            case 1:
                checkApplicantProcess();
                mainMenuPrompt();
                break;

            case 2:
                distributeTicketsProcess();
                mainMenuPrompt();
                break;

            case 3:
                quit();
                break;
            default:
                System.out.println("Please input a valid integer.");
                mainMenuPrompt();
                break;
        }

    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for a string first name
     * 
     * @return String of first name
     */
    public String firstNamePrompt()
    {
        String firstName = "";

        while (firstName.equals("") || isNumeric(firstName))
        {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine();
            if (firstName.equals("") || isNumeric(firstName))
            {
                System.out.println("Please input a valid String.");
            }
        }

        return firstName;
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for a string last name
     * 
     * @return String of first name
     */
    public String lastNamePrompt()
    {
        String lastName = "";

        while (lastName.equals("") || isNumeric(lastName))
        {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();
            if (lastName.equals("") || isNumeric(lastName))
            {
                System.out.println("Please input a valid String.");
            }
        }

        return lastName;
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for a event title
     * 
     * @return String of first name
     */
    public String eventTitlePrompt()
    {
        String eventTitle = "";

        while (eventTitle.equals("") || isNumeric(eventTitle))
        {
            System.out.print("Enter event title: ");
            eventTitle = scanner.nextLine();
            if (eventTitle.equals("") || isNumeric(eventTitle))
            {
                System.out.println("Please input a valid title.");
            }
        }

        return eventTitle;
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for a clearance level
     * 
     * @return int of clearance level
     */
    public int clearanceLevelPrompt()
    {
        int clearance = -1;

        while (clearance == -1)
        {
            System.out.print("Enter clearance level: ");

            try
            {
                clearance = Integer.parseInt(scanner.nextLine());
                if (0 > clearance)
                {
                    System.out.println("Please input a valid clearance level.");
                    clearance = -1;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please input a valid clearance level.");
            }
        }

        return clearance;
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for a integer id that must be 0<=id<Applicant.globalID
     * 
     * @return ID integer
     */
    public int idPrompt()
    {
        int id = -1;

        while (id == -1 && Applicant.globalID != 0)
        {
            System.out
                .println("The most recent ID was " + (Applicant.globalID - 1));
            System.out.print("Enter ID: ");

            try
            {
                id = Integer.parseInt(scanner.nextLine());
                if (0 > id || id >= Applicant.globalID)
                {
                    System.out.println("Please input a valid ID.");
                    id = -1;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please input a valid ID.");
                id = -1;
            }
        }

        if (Applicant.globalID == 0)
        {
            System.out.println("There are currently no Applicants.");
        }

        return id;
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for an integer to determine if they want to redo the
     * latest action
     * 
     * @return true if the user inputs 0 and wants to redo the action
     */
    public boolean redoActionPrompt()
    {
        int choice = -1;

        while (choice == -1)
        {
            System.out.println(
                "----------------------------------------------------------\n"
                    + "What do you want to do?\n" + "0 - Redo Action\n"
                    + "1 - Return to Main Menu");
            System.out.print("Enter choice: ");

            try
            {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice != 0 && choice != 1)
                {
                    System.out.println("Please input a valid integer.");
                    choice = -1;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please input a valid integer.");
                choice = -1;
            }
        }

        return (choice == 0);
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for an integer to determine if they want to delete the
     * winners
     * 
     * @return true if the user inputs 0 and wants to delete the winners
     */
    public boolean deleteTicketWinnersPrompt()
    {
        int choice = -1;

        while (choice == -1)
        {
            System.out.println(
                "----------------------------------------------------------\n"
                    + "What do you want to do?\n" + "0 - Delete Winners\n"
                    + "1 - Return to Main Menu");
            System.out.print("Enter choice: ");

            try
            {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice != 0 && choice != 1)
                {
                    System.out.println("Please input a valid integer.");
                    choice = -1;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please input a valid integer.");
                choice = -1;
            }
        }

        return (choice == 0);
    }


    // ----------------------------------------------------------
    /**
     * Prompts the user for an integer (0 to amount of events) where 0 is quit
     * and non-0 represents an event to add to.
     * 
     * @return Event selected via prompt.
     */
    public Event addApplicantToEventPrompt()
    {
        int choice = -1;
        Event[] eventList = eventManager.getEventList();
        Event returnEvent = null;

        while (choice == -1)
        {
            System.out.println(
                "----------------------------------------------------------\n"
                    + "Which event do you want to add to?\n" + "0 - Quit");

            for (int i = 0; i < eventList.length; i++)
            {
                System.out
                    .println(i + 1 + " - " + eventList[i].getEventTitle());
            }
            System.out.print("Enter choice: ");

            try
            {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > eventList.length)
                {
                    System.out.println("Please input a valid integer.");
                    choice = -1;
                }
                else if (choice == 0)
                {
                    return null;
                }
                else
                {
                    returnEvent = eventList[choice - 1];
                    System.out.println(
                        "Adding applicant to " + returnEvent.getEventTitle());
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please input a valid integer.");
                choice = -1;
            }
        }

        return returnEvent;

    }


    // ----------------------------------------------------------
    /**
     * Stops program execution
     */
    public void quit()
    {
        System.out.println(
            "----------------------------------------------------------\n"
                + "Thanks for using TicketQuikIt!");
    }


    // ----------------------------------------------------------
    /**
     * Runs the main loop
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        Event[] eventArray = { new Event("Event 1", 1),
            new Event("Event 2", 5), new Event("Event 3", 10) };
        EventManager eventManager = new EventManager(eventArray);
        Menu ticketQuikIt = new Menu(eventManager);
        ticketQuikIt.mainMenuPrompt();
    }
}
