# Shinobi Management API

## 1. Project Overview
This project is a Java-based API for managing a Shinobi database. [cite_start]It demonstrates advanced OOP principles, JDBC integration with PostgreSQL, and a multi-layer architecture[cite: 1, 9].

**Entities:**
* [cite_start]**BaseEntity**: Abstract base class for all entities [cite: 13-14].
* [cite_start]**Shinobi**: Abstract class extending BaseEntity, representing a ninja [cite: 11-13].
* [cite_start]**WarriorNinja & MedicNinja**: Concrete subclasses demonstrating inheritance and polymorphism[cite: 21].
* [cite_start]**Team**: Demonstrates composition by grouping Shinobi objects [cite: 25-26].

## 2. OOP Design
* [cite_start]**Abstraction**: `Shinobi` class defines abstract methods `performJutsu()` and `getNinjaWay()`[cite: 17, 23].
* [cite_start]**Polymorphism**: In `Main.java`, different types of ninjas are stored in a `List<Shinobi>` and execute their specific jutsu[cite: 24, 74].
* [cite_start]**Encapsulation**: All fields are private with public getters and setters[cite: 19].
* [cite_start]**Interfaces**: Implemented `JutsuCaster` and `MissionAssignable` to define common behaviors [cite: 28-29].

## 3. Database Schema
[cite_start]The database consists of the following tables [cite: 62-65]:
* **shinobi**: Stores all ninja data (id, name, chakra_level, dtype).
* **teams**: (Optional) Stores team information with foreign keys to shinobi.

```sql
[cite_start]-- Example Schema [cite: 120]
CREATE TABLE shinobi (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    chakra_level INT NOT NULL,
    dtype VARCHAR(50) NOT NULL
);