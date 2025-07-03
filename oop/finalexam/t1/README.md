# 🧮 Final Task 1 — Java List Transformation Program

**Package:** `oop.finalexam.t1`

---

## 📋 Description

This Java program performs a **custom list transformation** based on an algorithm shown in [max.ge/final/t1/75629103/t1.html](http://max.ge/final/t1/75629103/t1.html). The logic is as follows:

### 👣 Steps:
1. **Initialize two lists:**
   - `list1` contains integers
   - `list2` contains strings
2. **Create list3** using:
   - For each `n` in `list1`, access the `(n + 1)`-th index of `list2`
   - Concatenate that string with the number `n` → `stringN`
   - Add `stringN` to `list3`
3. **Delete elements from list3** using indices from `list1`:
   - Remove item at index `n` from `list3`
   - Use **descending order** to avoid index shifting
   - If index is invalid, print a descriptive error

This logic ensures list3 is created and reduced properly, handling all edge cases.

---

## ✅ Example Input:
```java
list1 = [7, 5, 8, 9, 6, 3, 10, 4, 2, 1];
list2 = ["ief", "yby", "xil", "dps", "qzb", "ssd", "izp", "wwt", "bfh", "nqm", "aoz", "kba"];
```

## 🔄 Output Flow
```
Creating list3 using list1 and list2...
Added to list3: bfh7
Added to list3: ssd5
Added to list3: aoz8
...

Removing elements from list3 using sorted indices from list1...
Removed element at index 10: xyz
[ERROR] Cannot remove at index 12: index out of bounds in list3.

Final list3: [...]
```

---

## 🛑 Error Handling

When accessing `list2[n + 1]` or removing from `list3[n]`, the program:
- Checks whether the index is within range
- If not, it skips and prints a warning:
```
[ERROR] Index 13 is out of bounds for list2 (n = 12). Skipping.
```

This matches the **behavior shown in the animation** on the test website.

---

## 📸 Error Handling Screenshot

Below is a screenshot illustrating out-of-bounds error detection:

![Error Screenshot](![Screenshot 2025-07-03 115654.png](Screenshot%202025-07-03%20115654.png)screenshot.png)

> 📌 Upload this screenshot manually from the site: [http://max.ge/final/t1/test.html](http://max.ge/final/t1/test.html)

---

## 🧠 JavaDoc Requirement

All classes and methods are fully documented with Javadoc, covering:
- Purpose of the class
- Step-by-step explanation of the transformation
- Parameter and return value descriptions

---

## 📁 Structure
```
src/
└── oop/
    └── finalexam/
        └── t1/
            └── ListTransform.java
README.md
screenshot.png
```

---

## 👨‍🎓 Author
**Paata Shvelidze**  
Final Java Exam Project — Task 1