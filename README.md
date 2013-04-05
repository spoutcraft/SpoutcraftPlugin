[![SpoutPlugin][Project Logo]][Homepage]

SpoutPlugin (formerly BukkitContrib) is a plugin for [Bukkit](http://www.bukkit.org), which extends the Bukkit API to provide features that aren't normally available. SpoutPlugin is a legacy project, and will be replaced by the [Spout voxel game platform](https://github.com/SpoutDev/Spout) when finished. 

[Homepage] | [Forums] | [Twitter] | [Facebook]

## Using
It's easy to get started! Simply [download the latest][Download] compatible SpoutPlugin jar, then place the jar in your [CraftBukkit](http://dl.bukkit.org) plugins folder. Simple as that! You can then add any other plugins that utilize SpoutPlugin, then fire up [Spoutcraft](https://github.com/Legacy/Spoutcraft) and play!

## Contributing
Like the project? Feel free to [donate] to help continue development!

Are you a talented programmer looking to contribute some code? We'd love the help!
* Open a pull request with your changes, following our [guidelines and coding standards](http://spout.in/prguide).
* Please follow the above guidelines for your pull request(s) accepted.
* For help setting up the project, keep reading!

## The license
SpoutPlugin is licensed under the [GNU Lesser General Public License Version 3][License].

[![Spout][Company Logo]](http://www.spout.org)

## Getting the source
The latest and greatest source can be found here on [GitHub][Source].

If you are using Git, use this command to clone the project:

    git clone git://github.com/Legacy/SpoutPlugin.git

Or download the [latest zip archive][Download Source].

## Compiling the source
SpoutPlugin uses Maven to handle its dependencies.

* Download and install [Maven 2 or 3](http://maven.apache.org/download.html)  
* Checkout this repo and run: `mvn clean install`

## Using with your project
If you're using [Maven](http://maven.apache.org/download.html) to manage project dependencies, simply include the following in your `pom.xml`:

    <dependency>
        <groupId>org.getspout</groupId>
        <artifactId>spoutplugin</artifactId>
        <version>1.5.1-R0.1-SNAPSHOT</version>
    </dependency>

If you do not already have repo.spout.org in your repository list, you will need to add this also:

    <repository>
        <id>spout-repo</id>
        <url>http://repo.spout.org</url>
    </repository>

If you'd prefer to manually import the latest .jar file, you can get it from our [download site][Download], or from our [build server][Builds]. [![Build Status][Build Icon]][Builds]

Want to know how to use the API? Check out the latest [Javadoc].

[Project Logo]: http://cdn.spout.org/spoutplugin-github.png
[Company Logo]: http://cdn.spout.org/spout-github.png
[Homepage]: http://www.spout.org
[Forums]: http://forums.spout.org
[License]: http://www.gnu.org/licenses/lgpl.html
[Source]: https://github.com/Legacy/SpoutPlugin
[Download]: http://get.spout.org/dev/spoutplugin.jar
[Download Source]: https://github.com/Legacy/SpoutPlugin/archive/master.zip
[Builds]: http://build.spout.org/job/SpoutPlugin
[Build Icon]: http://build.spout.org/job/SpoutPlugin/badge/icon
[Javadoc]: http://jd.spout.org/legacy
[Issues]: http://issues.spout.org/browse/LEGACY
[Twitter]: http://spout.in/twitter
[Facebook]: http://spout.in/facebook
[Donate]: http://spout.in/donate
