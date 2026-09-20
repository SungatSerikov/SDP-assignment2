# Assignment 2: Factory Method and Abstract Factory

## Overview

This project is a Java console application created for Assignment 2 of the Software Design Patterns course.

The application demonstrates two creational design patterns:

- Factory Method
- Abstract Factory

The program simulates a logistics system where the user selects:

- a delivery mode: `ROAD` or `SEA`
- a UI platform: `WINDOWS` or `MACOS`

Factory Method is responsible for creating the appropriate transport.

Abstract Factory is responsible for creating a matching family of UI components.

---

## Technologies

- Java
- JDK 17
- IntelliJ IDEA

No database, graphical framework, web framework, or external services are required.

---

## Project Structure

```text
src
├── app
│   ├── Main.java
│   └── DeliveryApplication.java
│
├── factorymethod
│   ├── Transport.java
│   ├── Truck.java
│   ├── Ship.java
│   ├── Logistics.java
│   ├── RoadLogistics.java
│   └── SeaLogistics.java
│
└── abstractfactory
    ├── Button.java
    ├── Checkbox.java
    ├── WindowsButton.java
    ├── WindowsCheckbox.java
    ├── MacOSButton.java
    ├── MacOSCheckbox.java
    ├── GUIFactory.java
    ├── WindowsFactory.java
    └── MacOSFactory.java
```

---

## Factory Method

The Factory Method pattern is used to create transport objects.

The `Transport` interface defines the common delivery behavior.

There are two concrete products:

- `Truck` for road delivery
- `Ship` for sea delivery

The abstract `Logistics` class declares the factory method:

```java
public abstract Transport createTransport();
```

It also provides the shared delivery workflow:

```java
public void planDelivery(String cargo, String destination) {
    Transport transport = createTransport();
    transport.deliver(cargo, destination);
}
```

The concrete creators are:

- `RoadLogistics`, which creates a `Truck`
- `SeaLogistics`, which creates a `Ship`

The delivery workflow depends on the `Transport` abstraction instead of concrete transport classes.

---

## Abstract Factory

The Abstract Factory pattern is used to create families of related UI components.

Two abstract products are defined:

- `Button`
- `Checkbox`

The `GUIFactory` interface defines:

```java
Button createButton();
Checkbox createCheckbox();
```

There are two concrete factories:

### WindowsFactory

Creates:

- `WindowsButton`
- `WindowsCheckbox`

### MacOSFactory

Creates:

- `MacOSButton`
- `MacOSCheckbox`

This ensures that the button and checkbox always belong to the same UI family.

---

## Application Integration

`DeliveryApplication` receives both dependencies through its constructor:

```java
public DeliveryApplication(GUIFactory guiFactory, Logistics logistics)
```

It obtains the UI components from the provided `GUIFactory` and uses the provided `Logistics` object for delivery.

The application does not create concrete buttons, checkboxes, trucks, or ships directly.

`Main` is responsible for reading and validating user input and selecting the appropriate concrete factory and logistics creator.

---

## Supported Input

### Delivery Mode

```text
ROAD
SEA
```

### UI Platform

```text
WINDOWS
MACOS
```

Input is case-insensitive because it is converted to uppercase before validation.

---

## Supported Configurations

The application supports all four combinations:

| Delivery Mode | UI Platform | Transport | UI Components |
|---|---|---|---|
| ROAD | WINDOWS | Truck | Windows Button + Windows Checkbox |
| SEA | WINDOWS | Ship | Windows Button + Windows Checkbox |
| ROAD | MACOS | Truck | macOS Button + macOS Checkbox |
| SEA | MACOS | Ship | macOS Button + macOS Checkbox |

---

## Prerequisites

Before running the project, make sure that:

- JDK 17 is installed
- IntelliJ IDEA or another Java IDE is available
- the project SDK is configured to use JDK 17

---

## How to Run in IntelliJ IDEA

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Open **File → Project Structure**.
4. Set the Project SDK to **JDK 17**.
5. Open:

```text
src/app/Main.java
```

6. Run the `Main` class.
7. Enter the delivery mode when prompted.
8. Enter the UI platform when prompted.

---

## Example Run

Example input:

```text
ROAD
WINDOWS
```

Example output:

```text
Enter delivery mode (ROAD or SEA): ROAD
Enter UI platform (WINDOWS or MACOS): WINDOWS

Delivery mode: ROAD
UI platform: WINDOWS
Rendering Windows button
Rendering Windows checkbox
Truck delivers laboratory equipment to Aktau warehouse
```

---

## Another Example

Input:

```text
SEA
MACOS
```

Output:

```text
Enter delivery mode (ROAD or SEA): SEA
Enter UI platform (WINDOWS or MACOS): MACOS

Delivery mode: SEA
UI platform: MACOS
Rendering macOS button
Rendering macOS checkbox
Ship delivers laboratory equipment to Aktau warehouse
```

---

## Invalid Input Handling

The program validates both user choices.

Example of an unsupported delivery mode:

```text
Enter delivery mode (ROAD or SEA): AIR
Unsupported delivery mode: AIR
```

The application stops without performing a delivery.

Example of an unsupported UI platform:

```text
Enter delivery mode (ROAD or SEA): ROAD
Enter UI platform (WINDOWS or MACOS): LINUX
Unsupported UI platform: LINUX
```

The application stops without constructing or rendering an invalid UI family.

Missing or empty input is also handled with a clear validation message.

---

## Design Patterns Used

### Factory Method

Factory Method allows subclasses of `Logistics` to decide which concrete `Transport` object should be created.

This separates the common delivery workflow from concrete transport creation.

### Abstract Factory

Abstract Factory provides an interface for creating families of related UI components.

It ensures that a button and checkbox from different platforms are not mixed together.

---

## Clean Code

The project follows several Clean Code practices:

- meaningful class and method names
- small methods with clear responsibilities
- shared delivery logic instead of duplicated code
- dependencies on interfaces and abstract classes
- encapsulation of behavior inside objects

The application uses abstractions such as `Transport`, `Button`, `Checkbox`, `GUIFactory`, and `Logistics` instead of depending directly on concrete implementation classes.

---

