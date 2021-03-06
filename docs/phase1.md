---
title: SWE206-52 group 5 \linebreak Project Phase One
---

| Name                       | ID       |
|----------------------------|:--------:|
|Abdullah Nawar Thubaiti     | 201951090|
|Mohammad Khalid Mulia       | 201948550|
|Mohammad Saher Alshairbeeny | 201914330|
|Mohammed Ahmed Al-Easi      | 201971350|

\newpage


# Non functional requirements

List of non-functional requirements:

- The system shall be easy to use
- The system must be responsive
- The system must store the data safely in an `.xlsx` format
- There can only be one instance of the system at any given moment
- The system must be written in `java`, and the GUI is to be implemented in
  `javaFX`.
- The system should look appealing to the user

# Use case description

## Send an email

1. The user Chooses a competition
2. The user Clicks on send email
3. The user's default email client is opened with the necessary information
filled out.

### Exceptions

3. The user has no default email client
    a) Inform the user that the system has failed to detect his or her default
    email client.

## Browse competition website

1. the user chooses a competition 
2. the user clicks on browse website
3. built in browser should open up viewing the competition website

### Alternative flow

2. The competition has no available website
    a) Inform the user that no website is associated with the selected
    competition


## Add a competition

1. The user clicks on the add competition button
2. The user is prompted to enter the name, date, and the information of the
   students participating in the competition
3. The user clicks OK
4. The system adds the competition and all of its related information 
5. The user clicks save
6. The entered data is stored in the data file.

### Alternative flows

2. The user enters invalid or missing information.

    a) The system informs the user about the missing information, and asks him or
      her to try again.

## Edit competition information

1. The uesr chooses competition from the competitions list.
2. The user clicks on the edit button
3. All information regarding the competition is displayed for the user to edit
4. The user makes his or her changes
5. The system updates the competition
6. Upon clicking save, the competition is stored within the data file

### Alternative flows

3. The user removes required information or enters invalid information.
    A) The system refuses to save the changes, unless the user enters valid
    infomration

## Remove a competition

1. The user chooses a competition from the competitions list
2. The user clicks on the delete button
3. The system shows a confirmatin alert
4. The user clicks yes to confirm
5. The competition is removed from the system, and will no longer be stored once
   the user clicks on save.

### Alternative flows

4. the user clicks no instead of yes.
    A) The system closes the alert and do nothing.

## View competition

1. The user clicks on a competition to view all of its associated information.
   The information includes: The students / teams participating in the
   competition, the competition due date, and competition website, and the
   competition description.

## View a list of due competitions

1. The user clicks on "view due competitions" button to view a list of competition that
   are due.


# Use case diagram

`TODO: put diagram here once we're done`
