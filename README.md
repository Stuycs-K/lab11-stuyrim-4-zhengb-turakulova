[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.


## Adventurer Subclasses

### NYU Exchange Student
Base Health: 25

Max Health: 30

Base Special (money): 5

Max Special: 10

#### Abilities
- Attack
  - Normal: deals 1 to 2 damage, and passively restores 1 special to attacker.
  - Special: The exchange student flaunts his bank account, rendering the opponent immobile for the next 1-2 rounds.
	- Consumes 1 daddy’s money.
- Ally Support
  - Description: The exchange student generously shares his money with everyone, boosting their special by 1 and health by 3.
  - Consumes 2 daddy’s money.
- Self Support
  - Description: The exchange student receives his weekly $10,000 deposit from his dad, boosting his health by 4.
  - Consumes 1 daddy’s money, but can only be used if daddy’s money is less than 3.

### Unpaid CS Intern
Base Health: 20

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
Base Health:  25
  
Max Health: 30

Base Special: 14

Special max: 16

#### Abilities
  - Fields: health, savings, savingsMax
- Self Support: check bank account
  - Health + 3 special + 2
- Attack: 
  - deals 1 to 2 damage, and passively restores 1 special to attacker.
  - Special: pass interview and take other’s job
  - Damage 4
  - Consumes: 3 savings for unemployment expenditures
