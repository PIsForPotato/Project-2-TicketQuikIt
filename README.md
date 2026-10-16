# TicketQuikIt

TicketQuikIt is a ticket queue managing program. It allows users to add applicants to ticket queues for various events and to retrieve the winning applicants on a first come first serve basis. 

### Compiling and Running in Eclipse
To compile and run the program in the Eclipse IDE, run Menu.java as a "Java Application" because it contains the main method necessary to initialize the program loop. 
Any other IDE should similarly run Menu.java to start the main method loop. 

### Running Tests in Eclipse
To run our JUnit tests, run any test classes as "JUnit Test." Test classes can be identified by the suffix Test in their file name (____Test.java). In this prohect, the following files should be ran for testing:
  - ApplicantTest
  - EventTest
  - EventManagerTest
  - LinkedChainTest

### Important note about JUnit testing
JUnit testing is done using the 'CS2-Support' package as provided by the CS 2114 class this project was built for. The project assumes that CS2-Support is both downloaded and within the project's build path. Anyone not in our university will not have access to said package. Therefore, unfortunately, our JUnit testing is not accessible to external viewers.

## Reflection
### Changes to Specification

**General**
- Fixed various typos including missing parentheses, missing return types, missing parameters, faulty big O notations, and incorrect exception handling. 

**New EventManager**
- Split original Menu class into Menu and EventManager class to better divide responsibilities. EventManager has the field eventList and the method checkApplicantsEvent(int id) that originally belonged to Menu
- EventManager additionally includes a getEventList() and findEvent(String title) with O(1) and O(n), respectively
- Included test cases for EventManager's new methods.

**Menu**
- Added private helper method isNumeric() to Menu, used for verifying Strings as a number, updated "Where Validation Lives" accordingly
- Add an EventManager object as a field for Menu
- Split Menu's mainMenuPrompt processes into private methods of addApplicantProcess(), checkApplicantProcess(), and distributeTicketsProcess() for readability. 

**Event**
- Redefine countApplicant(int id) to iterate through the LinkedChain instead of using count(T anEntry); this is to better use ID values
- Add hashCode(), aligning with overriden equals()
- Add toString() method

**Applicant**
- getFirstName() and getLastName() return null if their respective values, firstName and lastName, are null. Used to update test plan.
- Add hashCode(), aligning with overriden equals()
- Add toString() method

### Changes to LinkedChain class
As of the current version, LinkedChain has not been changed.

### Updates to Unknowns
The following unknowns have been addressed
- **"The ID field for Applicants should increment with each newly created Applicant and the total count should be kept track of; it is currently unknown how this will be achieved"**
  - globalID system (a public static int) resolves the incrementing ID feature of Applicant
- **"How a function will check through all of the event queues and identify whether a specific applicant is present based on the applicant ID"**
  - A function can check through all event queues and identify specific individuals by keeping track of the events queues in an array and iterating through each individual event to search for an ID as needed. EventManager's checkApplicantsEvent(int id) implements this.
- **"How a single linked list class will be able to store and handle two different classes inside"**
  - A single Linked List can store and handle two different classes by using generics.
- **How to implement a clearance level within our code (how and where it would be appropriate to add it) to meet our stretch goal**
  - Clearance level is stored on Applicants and checked against a security level stored on Events before an Applicant is added to an Event.

### What Doesn't Work Yet
Everything initially specified in our Minimum Viable Product (MVP) spec is functional. Outside of our MVP, the stretch goal of allowing the user to create and manage new Event objects is unimplemented. There is currently no way for the user to edit Event objects at all via the main

## Next Steps
- **What is left to finish the MVP? The remaining features and fixes, in the order you will do them.**
  - The MVP is currently finished. All methods and features listed in the spec and scope function. 
- **Which stretch goals will you attempt? The ones from your scope you are committing to, and the ones you are dropping.**
  - Provide the user the ability to manually add new Events
    - Committed; not implemented yet
    - Will require new Menu processes and prompting
      - public void addEventProcess(String eventTitle, int eventCapacity, int security)
      - private int eventCapacityPrompt()
    - EventManager requires a new method to add Events
  - Clearance Level
    - Committed; functionally implemented
    - Although it works functionally, it is missing test cases
- **What new tests will you need? The methods and bad-input cases the added work must cover.**
  - addEventProcess()
    -  Normal Case: Adding an Event with valid inputs in each parameter creates a valid Event that can be accessed via other menu options
    -  Bad Input: Adding an Event with any invalid input in parameters (null/empty eventTitle; 0, negative, or non-int eventCapacity; 0, negative, or non-int security) does not create an Event object and, instead, informs the user of invalidity
  - getSecurity()
    - Normal Case: Returns Event's security level
    - Bad Input: Returns null if security is invalid
  - getClearance() 
    - Normal case: Returns Applicant's clearance level
    - Bad Input: Returns null if clearance is invalid
  -   Event's addApplicant() require new test cases that account for clearance level.
    - Normal Case: Adding an Applicant with a clearance higher than an Event's security
    - Bad Input: Adding an Applicant with a clearance lower than an Event's security
- **Who does what? Which team member owns each remaining item.**
  - Clearance level testing: Makayla Irwin
  - Event adding implementation: Alex Zhao, Lehiem Seyoum
  - Event adding testing: Nandini Duggaraju

## Attribution Table 
| Class | Contributor(s) | Contribution |
| --- | --- | --- |
| Menu | Alex Zhao | Wrote, revised, completed |
| Applicant | Makayla Irwin | Wrote, revised, completed |
| ApplicantTest | Makayla Irwin | Wrote, revised, completed |
| Event | Lehiem Seyoum | Wrote, revised, completed |
| EventTest | Lehiem Seyoum | Wrote, revised, completed |
| EventManager | Alex Zhao | Wrote, revised, completed |
| EventManagerTest | Alex Zhao | Wrote, revised, completed |
| LinkedChain | CS 2114 Staff | Wrote, revised, completed |
| LinkedChainTest | Nandini Duggaraju | Wrote, revised, completed |

**GenAI Note**: As of the current version, GenAI has not been used. 

