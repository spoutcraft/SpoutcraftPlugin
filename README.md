SpoutcraftPlugin
================
[![Build Status](https://travis-ci.org/Spoutcraft/SpoutcraftPlugin.png?branch=master)](https://travis-ci.org/Spoutcraft/SpoutcraftPlugin) [![Coverage Status](https://coveralls.io/repos/Spoutcraft/SpoutcraftPlugin/badge.png)](https://coveralls.io/r/Spoutcraft/SpoutcraftPlugin)  
SpoutcraftPlugin is a plugin for [Bukkit](http://www.bukkit.org), which extends the Bukkit API to provide features that aren't normally available. 

[Homepage] | [Forums] | [Twitter] | [Facebook]

## Getting started
It's easy to get started! Simply [download the latest][Download] compatible SpoutcraftPlugin jar, then place the jar in your [CraftBukkit](http://dl.bukkit.org) plugins folder. Simple as that! You can then add any other plugins that utilize SpoutcraftPlugin, then fire up [Spoutcraft](https://github.com/Spoutcraft/Spoutcraft) and play!

## Contributing
Like the project? Feel free to [donate] to help continue development!

Are you a talented programmer looking to contribute some code? We'd love the help!
* Open a pull request with your changes, following our [guidelines and coding standards](CONTRIBUTING.md).
* Please follow the above guidelines for your pull request(s) accepted.
* For help setting up the project, keep reading!

## The license
SpoutcraftPlugin is licensed under the [GNU Lesser General Public License Version 3][License].

## Getting the source
The latest and greatest source can be found here on [GitHub][Source].

If you are using Git, use this command to clone the project:

    git clone git://github.com/Spoutcraft/SpoutcraftPlugin.git

Or download the [latest zip archive][Source Download].

## Compiling the source
SpoutcraftPlugin uses Maven to handle its dependencies.

* Download and install [Maven 2 or 3](http://maven.apache.org/download.html)  
* Checkout this repo and run: `mvn clean install`

## Using with your project
If you're using [Maven](http://maven.apache.org/download.html) to manage project dependencies, simply include the following in your `pom.xml`:

    <dependency>
        <groupId>org.spoutcraft</groupId>
        <artifactId>spoutcraftplugin</artifactId>
        <version>1.6.4-R2.1-SNAPSHOT</version>
    </dependency>

If you do not already have the required repo in your repository list, you will need to add this as well:

    <repository>
        <id>sonatype-nexus-releases</id>
        <url>https://oss.sonatype.org/content/repositories/releases</url>
    </repository>
    <repository>
        <id>sonatype-nexus-snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>

If you'd prefer to manually import the latest .jar file, you can get it from our [download site][Download].

Want to know how to use the API? Check out the latest [docs][Docs].

[Homepage]: http://spoutcraft.org/
[Forums]: http://spoutcraft.org/forums/
[License]: http://www.gnu.org/licenses/lgpl.html
[Source]: https://github.com/Spoutcraft/SpoutcraftPlugin
[Download]: http://spoutcraft.org/downloads/
[Source Download]: https://github.com/Spoutcraft/SpoutcraftPlugin/archive/master.zip
[Docs]: https://spoutcraft.github.com
[Issues]: http://spoutcraft.org/issues/
[Twitter]: https://twitter.com/Spoutcraft
[Facebook]: http://www.facebook.com/pages/Spoutcraft/351909024946422
[Donate]: http://spoutcraft.org/donate/
