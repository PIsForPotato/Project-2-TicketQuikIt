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

General
- Fixed various typos including missing parentheses, missing return types, missing parameters, faulty big O notations, and incorrect exception handling. 

New EventManager
- Split original Menu class into Menu and EventManager class to better divide responsibilities. EventManager has the field eventList and the method checkApplicantsEvent(int id) that originally belonged to Menu
- EventManager additionally includes a getEventList() and findEvent(String title) with O(1) and O(n), respectively
- Included test cases for EventManager's new methods.

Menu
- Added private helper method isNumeric() to Menu, used for verifying Strings as a number, updated "Where Validation Lives" accordingly
- Add an EventManager object as a field for Menu

Event
- Redefine countApplicant(int id) to iterate through the LinkedChain instead of using count(T anEntry); this is to better use ID values
- Add hashCode(), aligning with overriden equals()

Applicant
- Add hashCode(), aligning with overriden equals()
- getFirstName() and getLastName() return null if their respective values, firstName and lastName, are null. Used to update test plan.

### Changes to LinkedChain class
As of the current version, LinkedChain has not been changed.

### Updates to Unknowns
The following unknowns have been addressed
- "The ID field for Applicants should increment with each newly created Applicant and the total count should be kept track of; it is currently unknown how this will be achieved"
  - globalID system (a public static int) resolves the incrementing ID feature of Applicant
- "How a function will check through all of the event queues and identify whether a specific applicant is present based on the applicant ID"
  - A function can check through all event queues and identify specific individuals by keeping track of the events queues in an array and iterating through each individual event to search for an ID as needed. EventManager's checkApplicantsEvent(int id) implements this.
- "How a single linked list class will be able to store and handle two different classes inside"
  - A single Linked List can store and handle two different classes by using generics. 

### What Doesn't Work Yet
- The implementation of the stretch goal of clearance levels is still unknown.
- Not available

## Next Steps
- Not available

## Attribution Table 
| Class | Contributor(s) | Contribution |
| --- | --- | --- |
| Menu | Alex Zhao | Wrote, revised, completed |
| Applicant | Makayla Irwin | Wrote, revised, completed |
| ApplicantTest | Makayla Irwin | Wrote, revised, completed |
| Event | N/A | N/A |
| EventTest | N/A | N/A |
| EventManager | Alex Zhao | Wrote, revised, completed |
| EventManagerTest | Alex Zhao | Wrote, revised, completed |
| LinkedChain | CS 2114 Staff | Wrote, revised, completed |
| LinkedChainTest | N/A | N/A |

**GenAI Note**: As of the current version, GenAI has not been used. 

