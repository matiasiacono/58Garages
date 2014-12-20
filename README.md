58Garages
=========

### LibGDX Cross-platform game development environment set-up

###### Things you'll need

* Java Development Kit
* Eclipse IDE
* Gradle plugin for Eclipse
* Android SDK (for targeting Android)
* RoboVM (for targeting iOS)


**Java Development Kit**

[Oracle Downloads Page](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html): download the one fits your system (Linux x64 in my case).

Just click that motherfucker 'til the Finish button.


```IMPORTANT: Create an JAVA_HOME env var pointing to the JDK folder.```


**Eclipse IDE**

Althought I sincerely hate Eclipse, the plugins we need work seamlessly with it (or that I hope most of the times, which are, err, nevah).

[Eclipse Downloads Page](https://www.eclipse.org/downloads/): download Eclipse "Standard" (Eclipse IDE for Java Developers), for your OS and architecture.

Uncompress it in a comfortable directory (such as ~/work/eclipse in Linux, C:\eclipse in Windows). Avoid spaces, weird simbols and large paths.
Run it and set the workspace to a reasonable dir.

**Android SDK**

[ADK Download Page](http://developer.android.com/sdk): download the one you need from the *SDK Tools Only* section. Same as with Eclipse, install it in a fitting directory (yes, we will have to make those dirs a convention).

```IMPORTANT: Create an ANDROID_HOME env var pointing to the ADK folder.```

Run at least once the SDK Manager from the ADK directory (*tools* directory), keep the suggested packages, if you are in W$ndows tick the USB drivers, too. Accept the EULAs and install everything.


**Eclipse Plugins (argh!)**

All these plugins are installed through *Help -> Install New Software*, or *Eclipse Marketplace*.
I will provide the URLs for the first method. Just copy and paste it into the URL text field on the wizard.

BTW, all these plugins will take a painful large time to download and install (and watch out for accepting EULAs windows and insecure software warnings). Yeap, Eclipse is a bitch.

* Android Development Tools plugin: http://dl.google.com/eclipse/plugin/4.3
* Google Plugin for Eclipse: https://dl.google.com/eclipse/plugin/4.4
* RoboVM plugin: http://download.robovm.org/eclipse
* Gradle IDE plugin: http://dist.springsource.com/release/TOOLS/gradle

```Make sure Eclipse is using UTF-8 line-endings going into Preferences -> General -> Workspace and selecting UTF-8 Encodings and Unix like endings```


**Environment Test project**

```Just in case, create a "local.properties" in the "cookbook" folder, and add a line with "sdk.dir={YOUR ADK INSTALLATION PATH}"```

1. Download the project I uploaded into the team's Google Drive. 
2. Uncompress it into your Eclipse's workspace.
3. Right-click in the package explorer, select *Import -> Gradle -> Gradle project*.
4. Select the [project]/cookbook folder, and click on *Build Model*
5. Once finished, tick all the projects and click *Finish*
6. If the Android project fails, right click on it, click on *Properties*, and tick your installed Android SDK.
7. Right click on the desktop project, and then *Run As -> Java Application*. If it fails, right click on the project, go to *Properties -> Run/Debug Settings*, edit the *Desktop Launcher* configuration, go to *Arguments*, and modify the *Working Directory* to *Other* with the follwing path:
```${workspace_loc:environment-test-android/assets}```

And I think that's it. You can always ask me (fmestevez@gmail.com) for further information.
Greets.
