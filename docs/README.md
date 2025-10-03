# Dennis

---

```
██████╗  ███████╗ ███╗   ██╗ ███╗   ██╗ ██╗ ███████╗
██╔══██╗ ██╔════╝ ████╗  ██║ ████╗  ██║ ██║ ██╔════╝
██║  ██║ █████╗   ██╔██╗ ██║ ██╔██╗ ██║ ██║ ███████╗
██║  ██║ ███╔══╝  ██║╚██╗██║ ██║╚██╗██║ ██║ ╚════██║
██████╔╝ ███████╗ ██║ ╚████║ ██║ ╚████║ ██║ ███████║
╚═════╝  ╚══════╝ ╚═╝  ╚═══╝ ╚═╝  ╚═══╝ ╚═╝ ╚══════╝
```

---

Dennis is a simple **command-line task manager** that helps you keep track of your to-dos, deadlines, and events. 

It allows you to add, list, search, mark, unmark, and delete tasks—all from your terminal.  

Dennis saves your tasks automatically to a file, so your list is restored every time you run it.

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/mukhtarcal/ip.git
cd ip
```

2. Compile the project:

```bash
javac -d bin $(find . -name "*.java")
```

3. Run Dennis:

```bash
java -cp bin Dennis.Dennis
```
---

## Features

Dennis supports three types of tasks:

1. **Todo** – a simple task with just a description.

2. **Deadline** – a task with a due date/time.

3. **Event** – a task with a start and end date/time.

---

## Usage

### View all tasks

```bash
list
```

### Add a Todo

```bash
todo read book
```

### Add a Deadline

With date:
```bash
deadline return book /by 2019-10-15
```

Or with time:

```bash
deadline return book /by 2/12/2019 1800
```

Or with plain text:

```bash
deadline return book /by tuesday
```

### Add an Event

With date:

```bash
event project meeting /from 2019-10-15 /to 2019-10-16
```

Or with time:

```bash
event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000
```

Or with plain text:

```bash
event project meeting /from Monday /to Wednesday
```

### Mark a Task by Task 1-indexed

```bash
mark 2
```

### Unmark a Task by Task 1-indexed
```bash
unmark 2
```

### Delete a Task

```bash
delete 3
```

### Find Tasks by Keyword or Keyphrase

```bash
find book
```

### Exit

```bash
bye
```

---

## Storage
All tasks are saved automatically in a file at: ./data/dennis.txt

```bash
data/dennis.txt
```

If the file or directory does not exist, Dennis creates it for you.

---

## Development Notes

Written in Java

Uses OOP principles (Commands, Parser, TaskList, Storage, UI separation)

