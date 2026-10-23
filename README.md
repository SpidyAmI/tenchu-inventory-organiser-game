# 🥷 Ninja Inventory Organiser

Desktop application written in **Java** with **JavaFX** for the UI. It is a gamified warehouse organiser for a ninja clan, built following OOP principles.

---

## 📖 Brief Description

**Ninja Inventory Organiser** lets you organise and monitor the clan's resources. Resources come as common and rare, and the rare ones have a chance to be unique and are locked at the start of the game. The application can be used with both keyboard shortcuts and mouse clicks, as described below.

<!-- TODO: add a screenshot of the application running, after the English translation is complete -->

---

## ✨ Key Features

- ➕ **Inventory Organiser:** click an item in the warehouse to move it to the ninja's equipment. The equipment has a limited number of slots.
- 🔍 **Special Actions (3-letter codes):** press `Start`, then type a code with the `W`, `A`, `S`, `D` keys (or the on-screen buttons):
    - `DWA`: unlocks all rare and unique items.
    - `DDA`: loot an enemy clan and boost the equipment slots (up to 99).
    - `DDS`: collect resources from your allies, adding one unit to every unlocked item.
- 📊 **Warehouse limits per single item:** common items → 8, rare items → 3, unique items → 1. The limit applies to each item individually and is not cumulative.
- 🎨 **Exception Handling:** the following situations are handled:
    - entering an invalid 3-letter code;
    - trying to equip an item while the equipment is full;
    - trying to equip an item that is out of stock;
    - trying to equip an item that is still locked.

  Each of them shows an error window and blocks the action without stopping the game.

---

## 🧬 Architecture and OOP Principles

*   **Inheritance:** `CommonItem` and `RareItem` extend `Item`, and `DDA`, `DDS` and `DWA` extend `Code`, reusing the shared state and behaviour of their base class.
*   **Polymorphism:** `GameEngine` runs every code through `CodeInterface.execute(...)`, and `Warehouse` treats common and rare items uniformly through `ItemInterface`.
*   **Encapsulation:** fields are private, item constructors are package-private (items are built via `FactoryItem`), and the methods that change the inventory capacity or unlock items are package-private so only the codes can call them.
*   **Abstractions and Interfaces:** `ItemInterface` and `CodeInterface` define the contracts, and the abstract classes `Item` and `Code` hold the common logic. New codes can be added without changing the rest of the code.
*   **MVC Pattern:** the `Model` packages hold the game rules, the `View` classes only display data and forward user input, and the `Controller` classes connect the two and refresh the views.

---

## 🛠️ System Requirements

- **Java Development Kit (JDK):** 25.0.1+
- **JavaFX SDK:** 25.0.2+
- **JUnit5**
- **Build Tool:** none (default IntelliJ IDEA project setup, no Maven or Gradle)

---

## ▶️ How to Run

1. Clone the repository and open it in **IntelliJ IDEA**.
2. Download the **JavaFX SDK** and add its `lib` folder as an external library: *File → Project Structure → Libraries → + → Java*, then select the `lib` folder of the SDK.
3. Run the `Launcher` class (**not** `Main`).

> **Why a `Launcher` class?** `Main` extends `javafx.application.Application`, and running such a class directly from the classpath fails unless JavaFX is configured as a module (VM options `--module-path` and `--add-modules`). `Launcher` does not extend `Application` and simply calls `Main.main(args)`, so the application starts without any extra VM options.

---

## 📁 Project Structure

Here is a tree view of the project file structure:

```
.
|-- src/
|   |-- Controller/
|   |   |-- Alerts/
|   |   |   |-- Alerts.java
|   |   |-- GameEngineController.java
|   |   |-- InventoryController.java
|   |   |-- ItemController.java
|   |   |-- WarehouseController.java
|   |-- Model/
|   |   |-- CodesAndWarehouse/
|   |   |   |-- Code.java
|   |   |   |-- CodeInterface.java
|   |   |   |-- DDA.java
|   |   |   |-- DDS.java
|   |   |   |-- DWA.java
|   |   |   |-- Inventory.java
|   |   |   |-- Warehouse.java
|   |   |-- Exceptions/
|   |   |   |-- InventoryFull.java
|   |   |   |-- NinjaException.java
|   |   |   |-- NotExistingCode.java
|   |   |   |-- NotUnlockedItem.java
|   |   |   |-- OutOfStockItem.java
|   |   |-- Items/
|   |   |   |-- CommonItem.java
|   |   |   |-- FactoryItem.java
|   |   |   |-- Item.java
|   |   |   |-- ItemInterface.java
|   |   |   |-- RareItem.java
|   |   |-- GameEngine.java
|   |-- View/
|   |   |-- InventoryView.java
|   |   |-- ItemView.java
|   |   |-- MainView.java
|   |   |-- WarehouseView.java
|   |-- launcher.java
|   |-- main.java
|-- test/
    |-- model/
        |-- CodesAndWarehouse/
        |   |-- InventoryTest.java
        |   |-- WarehouseTest.java
        |-- Items/
        |   |-- ItemTest.java
        |-- GameEngineTest.java

```

---

## 🗺️ Roadmap

- [X] Add unit tests (JUnit 5)
- [X] Translate code and comments to English
- [ ] Add screenshots
- [X] Complete the OOP principles section
- [X] Add the project tree in the Project Structure section