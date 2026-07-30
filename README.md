# 🏰 Guilds & Territories

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.20+-brightgreen.svg)](https://papermc.io/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/Build-Passing-success.svg)](#)

> **A modern, feature-rich Minecraft Spigot/Paper plugin for managing Guilds, Territory Claims, Upgrades, and Warfare with a clean GUI interface.**

---

## ✨ Key Features

- **🏰 Guild Creation & Management**: Create, join, and lead guilds with custom ranks, permissions, and roles.
- **🗺️ Territory Claiming System**: Secure your land, set up custom claim borders, and protect your resources.
- **⚡ Modern GUI Interface**: Interactive custom menus for easy navigation, land upgrades, and guild settings.
- **⚔️ Warfare & Alliances**: Form alliances, declare rivalries, and wage territory wars with real-time stats.
- **💎 Guild Vault & Upgrades**: Dedicated guild storage, shared bank, and unlockable perks/perk trees.

---

## 🛠️ Installation & Setup

1. **Download**: Grab the latest release `.jar` file.
2. **Deploy**: Place the `.jar` into your Minecraft server's `plugins/` directory.
3. **Configure**: Restart your server to generate the configuration files in `plugins/GuildsAndTerritories/`.
4. **Permissions**: Configure rank permissions using your preferred permission manager (e.g., LuckPerms).

---

## ⚡ Commands & Permissions

| Command | Description | Permission |
| :--- | :--- | :--- |
| `/guild create <name>` | Create a new guild | `guilds.create` |
| `/guild menu` | Open the modern Guild GUI dashboard | `guilds.user` |
| `/guild claim` | Claim the chunk you are currently standing in | `guilds.claim` |
| `/guild ally <guild>` | Send an alliance request | `guilds.ally` |
| `/guild admin` | Open administrative control panel | `guilds.admin` |

---

## 💻 Building from Source

Build the project using Maven:

```bash
git clone https://github.com/your-repo/Guilds-And-Territories.git
cd Guilds-And-Territories
mvn clean package
```

---

## 📝 License

This project is licensed under the **MIT License**.
