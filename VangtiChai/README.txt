VangtiChai - CSE 489 Assignment 1
===================================

App Name: VangtiChai (ভাংতি চাই - "I need change")
Course: CSE 489 - Mobile Application Development
Language: Kotlin
Min SDK: API 21 (Android 5.0 Lollipop)
Target SDK: API 34

Description:
------------
VangtiChai is a Bangladeshi Taka change calculator app. The user enters an
amount using a custom numeric keypad, and the app instantly calculates and
displays how many of each note denomination are needed to make up that amount.

Supported note denominations: 500, 100, 50, 20, 10, 5, 2, 1 Taka

Features:
---------
1. Custom numeric keypad (digits 0-9 + CLR button) implemented with ConstraintLayout
2. Real-time change calculation using greedy algorithm (largest denomination first)
3. Portrait and landscape layout support for both phones and tablets
4. State preserved on screen rotation (via onSaveInstanceState)
5. No hardcoded dp/sp values in layout XML — all values in dimens.xml
6. Responsive layouts for multiple screen sizes using Android resource qualifiers

Layout Files:
-------------
- layout/activity_main.xml          → Phone portrait (default)
- layout-land/activity_main.xml     → Phone landscape
- layout-sw600dp/activity_main.xml  → Tablet portrait (sw >= 600dp)
- layout-sw600dp-land/activity_main.xml → Tablet landscape

Dimension Files:
----------------
- values/dimens.xml                 → Phone portrait dimensions
- values-land/dimens.xml            → Phone landscape dimensions
- values-sw600dp/dimens.xml         → Tablet portrait dimensions
- values-sw600dp-land/dimens.xml    → Tablet landscape dimensions

Devices Tested:
---------------
1. Pixel XL (411 x 731 dp) - Portrait
2. Pixel XL (411 x 731 dp) - Landscape
3. Nexus 10 (800 x 1280 dp) - Portrait
4. Nexus 10 (800 x 1280 dp) - Landscape
5. Pixel 4 (393 x 830 dp) - Portrait
6. Pixel 4 (393 x 830 dp) - Landscape
7. Pixel Tablet (1280 x 800 dp) - Portrait and Landscape

Design Notes:
-------------
- Dark navy (#1A1A2E) background for a modern, premium look
- Gold (#F5C518) accent for note/count table headers
- Red (#E94560) CLR button for clear visual distinction
- All layouts use ConstraintLayout as recommended in the assignment

Change Calculation Algorithm:
------------------------------
The app uses a greedy algorithm: for each denomination (500, 100, 50, 20, 10,
5, 2, 1), it computes how many notes of that denomination fit into the remaining
amount, subtracts them, and repeats for the next denomination.

Example: Amount = 1234 Taka
  500: 2 notes  (remaining: 234)
  100: 2 notes  (remaining: 34)
   50: 0 notes  (remaining: 34)
   20: 1 note   (remaining: 14)
   10: 1 note   (remaining: 4)
    5: 0 notes  (remaining: 4)
    2: 2 notes  (remaining: 0)
    1: 0 notes

Build Instructions:
-------------------
1. Open project in Android Studio
2. Sync Gradle files
3. Run on emulator: Pixel XL or Nexus 10
4. Or build APK: Build > Build Bundle(s)/APK(s) > Build APK(s)
