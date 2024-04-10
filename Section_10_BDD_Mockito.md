## Behavior Driven Development

* Commonly abbreviated as BDD
* BDD started in 2004 by Dan North
* Dan North has said BDD was established to help people learn TDD
* BDD is largely a different way of looking at testing
* BDD focuses on behavior vs "tests"
* "Unit Tests" are referred to as specification - ie specifications of behaviors
* Test method names should be sentences - ie saveValidID

## Given When Then

* BDD Tests are often written in a given-when-then context
* This approach often helps you organize your thoughts when writing the test
* Given - Setup of the text
* When - Action of the test - ie when method is called
* Then - Verification of expected results

## Mockito and BDD

* Mockito has added BDD Support in class BDDMockito
* static method given allows you to configure mocks
* static method then allows you to verify mock interactions
