# Code Style Guidelines for Quiz-app

Our code style guidelines is based on the official [AOSP Java Code Style for Contributors]( https://source.android.com/setup/contribute/code-style).

## Important guidelines

#### Indentation
Use 4 spaces per indentation level and no tabs.

### Comments
Use in-line commenting to help the next developer who might be editing your code, even if it seems obvious now. Inline comments should appear on the line above the code your are commenting. Comment XML View elements using <!-- Comment -->.
Use block style comments for classes and functions. Every class, method and constant should have a javadoc style comment. Wherever the code is complex, explain using multi line comments.

### File naming

#### Class files
Class names are written in [UpperCamelCase](http://en.wikipedia.org/wiki/CamelCase).

For classes that extend an Android component, the name of the class should end with the name of the component; for example: `SignInActivity`, `SignInFragment`, `ImageUploaderService`, `ChangePasswordDialog`.

#### Resources files

Resources file names are written in __lowercase_underscore__.

#### Drawable files

Naming conventions for drawables:


| Asset Type   | Prefix            |        Example               |
|--------------| ------------------|-----------------------------|
| Action bar   | `ab_`             | `ab_stacked.9.png`          |
| Button       | `btn_`             | `btn_send_pressed.9.png`    |
| Dialog       | `dialog_`         | `dialog_top.9.png`          |
| Divider      | `divider_`        | `divider_horizontal.9.png`  |
| Icon         | `ic_`              | `ic_star.png`               |
| Menu         | `menu_ `           | `menu_submenu_bg.9.png`     |
| Notification | `notification_`    | `notification_bg.9.png`     |
| Tabs         | `tab_`            | `tab_pressed.9.png`         |

Naming conventions for icons (taken from [Android iconography guidelines](http://developer.android.com/design/style/iconography.html)):

| Asset Type                      | Prefix             | Example                      |
| --------------------------------| ----------------   | ---------------------------- |
| Icons                           | `ic_`              | `ic_star.png`                |
| Launcher icons                  | `ic_launcher`      | `ic_launcher_calendar.png`   |
| Menu icons and Action Bar icons | `ic_menu`          | `ic_menu_archive.png`        |
| Status bar icons                | `ic_stat_notify`   | `ic_stat_notify_msg.png`     |
| Tab icons                       | `ic_tab`           | `ic_tab_recent.png`          |
| Dialog icons                    | `ic_dialog`        | `ic_dialog_info.png`         |

Naming conventions for selector states:

| State        | Suffix          | Example                     |
|--------------|-----------------|-----------------------------|
| Normal       | `_normal`       | `btn_order_normal.9.png`    |
| Pressed      | `_pressed`      | `btn_order_pressed.9.png`   |
| Focused      | `_focused`      | `btn_order_focused.9.png`   |
| Disabled     | `_disabled`     | `btn_order_disabled.9.png`  |
| Selected     | `_selected`     | `btn_order_selected.9.png`  |


#### Layout files

Layout files should match the name of the Android components that they are intended for but moving the top level component name to the beginning. For example, if we are creating a layout for the `SignInActivity`, the name of the layout file should be `activity_sign_in.xml`.

#### Id names
Layout resource ids should use the following naming convention where possible:
```<layout name>_<object type>_<object name>```

#### Example
```
home_listview_quizzes
```


Follow Field Naming Conventions
- Non-public, non-static field names start with m.
- Other fields start with a lower case letter.
- Static final fields (constants) are ALL_CAPS_WITH_UNDERSCORES.

#### Example
```
public class MyClass {
    public static final int SOME_CONSTANT = 42;
    public int publicField;
    int mPackagePrivate;
    private int mPrivate;
    protected int mProtected;
}
```

Loop variables should be declared in the for statement itself unless there is a compelling reason to do otherwise:
```
for (int i = 0; i < n; i++) {
    doSomething(i);
}
```

### Use standard brace style
Braces do not go on their own line; they go on the same line as the code before them:

#### Example
```
class MyClass {
    int func() {
        if (something) {
            // ...
        } else if (somethingElse) {
            // ...
        } else {
            // ...
        }
    }
}
```
## For In-Depth Readers:

### Project Guidelines

Refer to this [source](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md).

Includes : File naming, parameter ordering in methods, class member ordering, etc.

### For XML files

Refer to this [source](https://jeroenmols.com/blog/2016/03/07/resourcenaming/).

