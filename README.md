# SimpleAntiChat

SimpleAntiChat is a lightweight Paper 1.21.x Java plugin that automatically deletes chat messages containing blocked words.  
It features warnings, staff notifications, and configurable punishments.

## Features

- Deletes messages with blocked words
- Tracks warnings per player
- Executes configurable punishment commands
- Staff notifications
- Fully configurable via `config.yml`

## Installation

1. Build the project using Maven: `mvn clean package`  
2. Place the generated `.jar` in your server's `plugins/` folder  
3. Start the server to generate the config  
4. Customize `config.yml` as needed

## Commands

- `/simpleantichat reload` – Reload configuration  
- `/simpleantichat warnings <player>` – View player warnings  
- `/simpleantichat clear <player>` – Clear player warnings

## Permissions

- `simpleantichat.admin` – Access all commands  
- `simpleantichat.bypass` – Bypass the chat filter  
- `simpleantichat.notify` – Receive blocked message notifications
