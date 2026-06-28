VangtiChai – CSE 489 Assignment 1
==================================

App Description
---------------
VangtiChai is an Android app that lets you enter a money amount via an on-screen
numeric keypad and instantly shows the minimum number of Bangladeshi Taka notes
(500, 100, 50, 20, 10, 5, 2, 1) required to make that amount.


Layout Structure
----------------
Four layout variants are provided so the app looks great on all target devices:

  res/layout/                  Phone portrait   (default)
  res/layout-land/             Phone landscape
  res/layout-sw600dp/          Tablet portrait  (sw600dp qualifier)
  res/layout-sw600dp-land/     Tablet landscape (sw600dp + land qualifiers)

Portrait layouts:
  - Top bar:  "Taka: <amount>"
  - Left 35%: change breakdown table (single column, 8 denominations)
  - Right 65%: 3-column numeric keypad (1-2-3 / 4-5-6 / 7-8-9 / 0-CLEAR)

Landscape layouts:
  - Top bar:  "Taka: <amount>"
  - Left 25%:  change column 1 (500, 100, 50, 20)
  - Mid  25%:  change column 2 (10, 5, 2, 1)
  - Right 50%: 4-column numeric keypad (1-2-3-4 / 5-6-7-8 / 9-0-CLEAR)

All dimension values (text sizes, margins, paddings, button heights) are defined
in res/values/sizes.xml (phones) and res/values-sw600dp/sizes.xml (tablets).
No values are hardcoded in layout XML files.


State Preservation
------------------
The entered amount is saved in onSaveInstanceState() and restored in
onCreate(savedInstanceState) so rotating the device does NOT reset the amount.
The Activity is deliberately NOT given android:configChanges in the manifest –
the system recreates it on rotation so the correct layout variant is inflated
automatically, demonstrating the Activity lifecycle as required.


Devices Tested
--------------
Required (emulator):
  Pixel XL   – 411 × 731 dp  – portrait  ✓
  Pixel XL   – 731 × 411 dp  – landscape ✓
  Nexus 10   – 800 × 1280 dp – portrait  ✓
  Nexus 10   – 1280 × 800 dp – landscape ✓

Additional (emulator):
  Pixel 4    – 393 × 830 dp  – portrait & landscape  ✓
  Pixel 7    – 411 × 914 dp  – portrait & landscape  ✓
  Nexus 7 (2013) – 600 × 960 dp – portrait & landscape ✓

Results were reasonable on all additional devices; layout adapts gracefully
thanks to the ConstraintLayout percentage guidelines and sw600dp qualifier.


Build Configuration
-------------------
  Language :  Kotlin
  Min SDK  :  API 21 (Android 5.0 Lollipop)
  Target SDK: API 34
  Layout   :  ConstraintLayout (all variants)
