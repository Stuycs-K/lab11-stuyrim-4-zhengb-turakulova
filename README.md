[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.


:white_check_mark: play with 3 different types adventurers

:white_check_mark: play against 1-3 randomly chosen adventurer opponents

:white_check_mark: use attack/special operations on your opponents

:white_check_mark: use support operations on your team

:white_check_mark: program ends when user quits, all enemies are defeated, or when user party is defeated. Win/Lose Screen.

:white_check_mark: display results of attack/special/support inside border. 

:ballot_box_with_check: differing turn sequence depending on if there are one or multiple enemies

:ballot_box_with_check: Error checking for input values with reprompting until a correct input

:ballot_box_with_check: fancy borders with unicode characters

## Adventurer Subclasses

### NYU Exchange Student
Base Health: 25-27

Max Health: 30

Base Special (money): 5

Max Special: 10

#### Abilities
- Attack
  - Normal: deals 1 to 2 damage, and passively restores 1 special to attacker.
  - Special: The exchange student flaunts his bank account, rendering the opponent immobile for the next 1-2 rounds.
	- Consumes 3 daddy’s money.
- Ally Support
  - Description: The exchange student generously shares his money with ally, boosting their special by 1 and health by 3.
  - Consumes 3 daddy’s money.
- Self Support
  - Description: The exchange student receives his weekly $10,000 deposit from his dad, boosting his health by 4.
  - Consumes 1 daddy’s money, but can only be used if daddy’s money is less than 3.

### Unpaid CS Intern
Base Health: 19-21

Max Health: 25

Base Special (work experience): 9

Max Special: 15

#### Abilities
- Attack
  - Normal: deals 1 to 2 damage, and passively restores 1 special to attacker.
  - Special: The unpaid intern shows their superior resume, shocking the opponent and dealing 4 points of damage.
	- Consumes 2 work experience
- Self Support
  - Description: The CS Intern locks in, boosting its damage by 1.3x for the next 2 rounds. Restores 2 health.
  - Consumes 3 work experience, if insufficient just restore 2 HP.
- Ally Support
  - Description: CS Intern advises ally to avoid unpaid internships and restores 1 special and 2 health
  - Consumes 2 work experience

### Boss: Senior SWE
Base Health:  48-50

Max Health: 55

Base Special: 14

Special max: 16

#### Abilities
- Self Support: check bank account
  - Health + 4 special + 2
- Attack:
  - Normal: deals 2 to 4 damage, and passively restores 1 special to attacker.
  - Special: pass interview and take other’s job
    - Damage 4-10
    - Consumes: 3 savings for unemployment expenditures