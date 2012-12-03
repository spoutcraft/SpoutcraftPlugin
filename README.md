SpoutPlugin
===========
SpoutPlugin is an API which extends the Bukkit API to allow plugin developers to have features that aren't normally in Bukkit.

Like the project? Feel free to [donate] to help continue development!

## What is SpoutPlugin?
SpoutPlugin (the Bukkit plugin, formerly BukkitContrib) is a plugin for Bukkit, which has a lot of classes that extend Bukkit classes, allowing for a huge amount of API those plugins can utilise to do a lot more than before. SpoutPlugin is a legacy project and will be replaced by the Spout server when finished. 

[![SpoutPlugin][Logo]][Homepage]  
[Homepage] | [Forums] | [Twitter] | [Facebook]

## The License
SpoutPlugin is licensed under the [GNU Lesser General Public License Version 3][License].

Copyright (c) 2011-2012, Spout LLC <<http://www.spout.org/>>

## Getting the Source
The latest and greatest source can be found here on [GitHub][Source].  
Download the latest builds from our [build server][Builds]. [![Build Status](http://build.spout.org/job/SpoutPlugin/badge/icon)][Builds]  
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
        <version>1.4.3-R0.2-SNAPSHOT</version>
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

[Logo]: http://cdn.spout.org/spoutplugin-github.png
[Homepage]: http://www.spout.org
[Forums]: http://forums.spout.org
[License]: http://www.gnu.org/licenses/lgpl.html
[Source]: https://github.com/SpoutDev/SpoutPlugin
[Javadoc]: http://jd.spout.org/legacy/plugin/latest
[Builds]: http://build.spout.org/job/SpoutPlugin
[Issues]: http://issues.spout.org/browse/SPOUTPLUGIN
[Twitter]: http://spout.in/twitter
[Facebook]: http://spout.in/facebook
[Donate]: http://spout.in/donate
