# 🏰 Guilds & Territories

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-1.20+-2E7D32?style=for-the-badge&logo=minecraft&logoColor=white" alt="Minecraft" />
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
  <img src="https://img.shields.io/badge/Spigot/Paper-API-F57C00?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Paper/Spigot" />
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License" />
</p>

> **A modern, high-performance Minecraft Spigot/Paper plugin for managing Guilds, Territory Claims, Upgrades, Economy, and Warfare with an intuitive GUI interface.**

---

## 🛠️ Tech Stack & Technologies

<p align="left">
  <a href="https://www.java.com"><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java" width="40" height="40"/></a> &nbsp;
  <a href="https://papermc.io"><img src="https://img.icons8.com/color/48/minecraft-cube.png" alt="Minecraft API" width="40" height="40"/></a> &nbsp;
  <a href="https://maven.apache.org"><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/maven/maven-original.svg" alt="Maven" width="40" height="40"/></a> &nbsp;
  <a href="https://projectlombok.org"><img src="https://objectcomputing.com/files/7015/8860/2650/lombok.png" alt="Lombok" width="40" height="40"/></a> &nbsp;
  <a href="https://git-scm.com"><img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/git/git-original.svg" alt="Git" width="40" height="40"/></a>
</p>

- **Language**: Java 17 (JDK 17)
- **API Framework**: Spigot / PaperMC API (1.20+)
- **Build System**: Apache Maven & Maven Shade Plugin
- **Helper Tools**: Project Lombok

---

## ✨ Key Features

- **🏰 Guild Creation & Management**: Create, join, and lead guilds with custom ranks, permissions, and roles.
- **🗺️ Territory Claiming System**: Secure your land, set up custom claim borders, and protect your resources.
- **⚡ Modern GUI Interface**: Interactive custom menus for easy navigation, land upgrades, and guild settings.
- **⚔️ Warfare & Alliances**: Form alliances, declare rivalries, and wage territory wars with real-time stats.
- **💎 Guild Vault & Upgrades**: Dedicated guild storage, shared bank, and unlockable perks/perk trees.
- **💰 Taxes & Economy**: Treasury management, automated taxes, and player economy hooks.

---

## ⚡ Commands & Permissions

| Command | Description | Permission |
| :--- | :--- | :--- |
| `/guild create <name>` | Create a new guild | `guilds.create` |
| `/guild menu` | Open the modern Guild GUI dashboard | `guilds.user` |
| `/guild claim` | Claim the chunk you are currently standing in | `guilds.claim` |
| `/guild ally <guild>` | Send an alliance request | `guilds.ally` |
| `/guild admin` | Open administrative control panel | `guilds.admin` |
| `/guild tax` | Manage or view guild tax settings | `guilds.tax` |
| `/guild war <guild>` | Declare war on a rival guild | `guilds.war` |

---

## 📥 Installation & Setup

1. **Download**: Grab the latest release `.jar` file.
2. **Deploy**: Place the `.jar` into your Minecraft server's `plugins/` directory.
3. **Configure**: Restart your server to generate configuration files in `plugins/GuildsAndTerritories/`.
4. **Permissions**: Configure rank permissions using your preferred permission manager (e.g., LuckPerms).

---

## 💻 Building from Source

Build the project using Maven:

```bash
git clone https://github.com/ranawise/Guilds-And-Territories.git
cd Guilds-And-Territories
mvn clean package
```

---

## 📝 License

This project is licensed under the **MIT License**.
