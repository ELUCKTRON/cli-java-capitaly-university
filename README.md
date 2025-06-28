# CapitalyGame

### 🧩 Game Overview

This CLI-based simulation models the Capitaly game with simplified rules and focuses on:
- Different types of **fields**: property, service, and lucky.
- Players using **strategy-based behavior**.
- A **cyclical board** to simulate movement.
- Basic **game economy** (buying, paying, winning conditions).

---

### 🎮 Game Mechanics

#### Fields
- **Property Field**
  - Can be bought for **1000**.
  - If a player steps on their own property again, they can build a **house** for **4000**.
  - Stepping on another player's property:
    - Pay **500** (if no house).
    - Pay **2000** (if house exists).
- **Service Field**
  - Pay a fixed amount to the **bank** (defined in the input file).
- **Lucky Field**
  - Gain a fixed amount from the **bank** (defined in the input file).

#### Players
- Start with **10000** money.
- Elimination: If a player cannot pay required money, they are eliminated, and their properties are released.

#### Player Strategies
- **Greedy**: Always buys if they have enough money.
- **Careful**: Only spends at most half their money in any round.
- **Tactical**: Skips every second chance to buy.

---

### 📄 Input

The game reads from a **text input file** that includes:
1. Number of fields and their types (plus price for service/lucky).
2. Number of players with name and strategy.
3. A sequence of dice rolls (for reproducibility and testing).

Example input structure:
```
5
property
lucky 200
service 300
property
property
3
Alice Greedy
Bob Careful
Charlie Tactical
4 6 2 1 5 3 4 ...
```

---

### 📦 Classes

| Class              | Description                                                       |
|-------------------|-------------------------------------------------------------------|
| `Player`           | Represents each player with name, money, position, and strategy. |
| `CyclicalBoard`    | Holds the fields and handles player movement around the board.   |
| `Dice`             | Reads dice rolls from input file.                                |
| `Field` (abstract) | Base for all field types.                                        |
| `PropertyField`    | Ownable and upgradable (with house).                             |
| `ServiceField`     | Deducts money from player.                                       |
| `LuckyField`       | Adds money to player.                                            |

---

### 🎓 Notes

- Built for the **Software Technology** course.
- University project, **Semester 3**.
- Fully self-contained CLI application with simulation logic.
- No external libraries required.

---

### 🏁 Output

At the end of the simulation, the game reports:
- The **winner's name**
- Their **final balance**
- Number of **owned properties**

---
```
