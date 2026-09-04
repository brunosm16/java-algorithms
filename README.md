# Java Data Structures and Algorithms

Small, focused Java implementations of common data structures and algorithms.

## Requirements

- Java 21
- Gradle, or an IDE with Gradle support

## Project Layout

```text
src/main/java/dev/algorithms
  algorithms/      Algorithm implementations
  datastructures/  Data structure implementations
  leetcode/        LeetCode challenge implementations

src/test/java/dev/algorithms
  algorithms/      Algorithm tests
  datastructures/  Data structure tests

docs/leetcode
  Markdown documentation for LeetCode challenge solutions
```

## Run Tests

```bash
gradle test
```

Every implementation should have JUnit tests that cover:

- Typical usage
- Boundary cases
- Invalid input or failure behavior
