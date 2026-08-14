VangtiChai (Assignment 1)
-------------------------
Course: CSE 489: Mobile Application Development
Project: VangtiChai app (Taka Note Change Calculator)

Tested Devices & Screen Sizes:
1. Pixel XL Phone (411 x 731 dp)
   - Portrait: Layout uses a 3x4 numeric keypad on the right, and a 1-column notes table on the left. The '0' button spans two columns and 'CLEAR' occupies the third.
   - Landscape: Layout automatically shifts to a 4x3 numeric keypad on the left (with a double-width 'CLEAR' button), and splits the notes table into 2 columns on the right to optimize horizontal screen space.

2. Nexus 10 Tablet (800 x 1280 dp)
   - Portrait: Uses the scaled-up portrait configuration defined in layout-sw600dp and values-sw600dp/sizes.xml. The margins, paddings, and text sizes are proportioned to fit the larger display without stretching.
   - Landscape: Uses the scaled-up landscape configuration defined in layout-sw600dp-land and values-sw600dp-land/sizes.xml.

3. Pixel 7 Phone (411 x 891 dp)
   - Verified that the ConstraintLayout chains and percentage guidelines adapt gracefully to taller aspect ratios in both portrait and landscape modes.

4. Nexus 9 Tablet (768 x 1024 dp)
   - Verified that tablet scaling functions correctly on a 4:3 screen ratio.

Design & Implementation Details:
- The user interface uses a premium dark-themed color palette.
- No hardcoded sizes, padding, or margins are present in the layout XMLs. All dimension properties are defined in respective `sizes.xml` files for values, values-land, values-sw600dp, and values-sw600dp-land resource folders.
- The state (current amount and note counts) is preserved properly across orientation changes by overriding onSaveInstanceState and restoring state in onCreate.
- Binary mipmap icons are avoided in favor of vector shape drawables in res/drawable/ to ensure clean building.
