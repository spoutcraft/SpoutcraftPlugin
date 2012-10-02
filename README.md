SpoutPlugin
===========
SpoutPlugin is the good ol' plugin for the CraftBukkit server. It uses a Java trick called reflection to modify certain parts of CraftBukkit to allow lots of new features, most of which require the Spoutcraft client to run. It also features an API which extends Bukkit's API to allow plugin developers to have features that aren't normally in Bukkit's standard API.

Visit our [website][Website] or get support on our [forums][Forums].  
View issues and bugs on our [issue tracker][Issues].

[![Follow us on Twitter][Twitter Logo]][Twitter][![Like us on Facebook][Facebook Logo]][Facebook][![Donate to the Spout project][Donate Logo]][Donate]

## The License
SpoutPlugin is licensed under the [GNU Lesser General Public License Version 3][License].

Copyright (c) 2011-2012, SpoutDev <<http://www.spout.org/>>  
[![Spout][Author Logo]][Website]

## Getting the Source
The latest and greatest source can be found on [GitHub].  
Download the latest builds from [Jenkins]. [![Build Status](http://build.spout.org/job/SpoutPlugin/badge/icon)][Jenkins]  
View the latest [Javadoc].

## Compiling the Source
SpoutPlugin uses Maven to handle its dependencies.

* Install [Maven 2 or 3](http://maven.apache.org/download.html)  
* Checkout this repo and run: `mvn clean install`

## Using with Your Project
For those using [Maven](http://maven.apache.org/download.html) to manage project dependencies, simply include the following in your pom.xml:

    <dependency>
        <groupId>org.getspout</groupId>
        <artifactId>spoutplugin</artifactId>
        <version>1.3.2-R1.0-SNAPSHOT</version>
    </dependency>

If you do not already have repo.spout.org in your repository list, you will need to add this also:

    <repository>
        <id>spout-repo</id>
        <url>http://repo.spout.org</url>
    </repository>

## Coding and Pull Request Conventions
* Generally follow the Oracle coding standards.
* No spaces, only tabs for indentation.
* No trailing whitespaces on new lines.
* 200 column limit for readability.
* Pull requests must compile, work, and be formatted properly.
* Sign-off on ALL your commits - this indicates you agree to the terms of our license.
* No merges should be included in pull requests unless the pull request's purpose is a merge.
* Number of commits in a pull request should be kept to *one commit* and all additional commits must be *squashed*.
* You may have more than one commit in a pull request if the commits are separate changes, otherwise squash them.
* For clarification, see the full pull request guidelines [here](http://spout.in/prguide).

**Please follow the above conventions if you want your pull request(s) accepted.**

[Author Logo]: http://cdn.spout.org/img/logo/spout_305x135.png
[License]: http://www.gnu.org/licenses/lgpl.html
[Website]: http://www.spout.org
[Forums]: http://forums.spout.org
[GitHub]: https://github.com/SpoutDev/SpoutPlugin
[Javadoc]: http://jd.spout.org/legacy/plugin/latest
[Jenkins]: http://build.spout.org/job/SpoutPlugin
[Issues]: http://issues.spout.org/browse/SpoutPlugin
[Twitter]: http://spout.in/twitter
[Twitter Logo]: http://cdn.spout.org/img/button/twitter_follow_us.png
[Facebook]: http://spout.in/facebook
[Facebook Logo]: http://cdn.spout.org/img/button/facebook_like_us.png
[Donate]: http://spout.in/donate
[Donate Logo]: http://cdn.spout.org/img/button/donate_paypal_96x96.png
