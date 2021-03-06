#### Resolution Modifier

Resolution Modifier is responsible for changing browser screen size. Affects [[Screen Collector | ScreenCollector]] results.

| ! Note |
|:------ |
| Firefox: Please note that final resolution of screenshots may be different when scrollbar is displayed. <br/><br/> Default  width of Firefox's Scrollbar is equal to 33px.  (so when you want to grab viewport of size 1024, then set width parameter to 1057px) |

Module name: **resolution**

##### Parameters

| Parameter | Value | Description | Mandatory |
| --------- | ----- | ----------- | --------- |
| `width` | int (1 to 35000) | Window width | yes |
| `height` | int (1 to 35000) | Window height | no |
| `samplingPeriod` | int (milliseconds) | Used when `height` is not defined. Defaults to 100ms (see notes below) | no |

TODO: Change 15000 to 35000 when (https://github.com/Cognifide/aet/pull/387) is merged.

| Note |
|:------ |
| When `height` is not specified then it's computed by JavaScript (using `document.body.scrollHeight` property). |
| For very long pages, it may take some time to render the page in order to get its full height, so AET is using an algorithm that samples the page's height over some specified period of time. `samplingPeriod` specifies the amount of time between taking each sample. If defined number of samples would match (3 last samples) or when the max number of samples is reached (15), the acquired valued is used as `height` resolution for screenshot.| 
| **If the resolution is specified without height parameter it should be specified after [`open`](https://github.com/Cognifide/aet/wiki/Open)** and after all modifiers which may affect the page height (e.g. [`hide`](https://github.com/Cognifide/aet/wiki/HideModifier))  |

##### Example Usage

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<suite name="test-suite" company="cognifide" project="project">
    <test name="resolution-modify-test">
        <collect>
            ...
            <resolution width="200" height="300"/>
            <sleep duration="1000"/>
            <screen />
            ...
        </collect>
        <compare>
            ...
        </compare>
        <urls>
        ...
        </urls>
    </test>
    ...
    <reports>
        ...
    </reports>
</suite>
```

#### Tips and tricks

In order to make sure that your screenshots have the resolution you expect them to have you need to test it first.
Run the test case your developing currently and check if the screenshot resolution fits your requirements.
If it's different from what you expected then you can try adding X pixels 
where X is the width of the browsers scrollbar.
Another thing that you can try is increasing the height of the screenshot to be 
greater or equal to the height of the page's content.
