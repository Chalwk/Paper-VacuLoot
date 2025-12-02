# VacuLoot

VacuLoot is an item magnet system, designed to automatically attract nearby items and experience orbs to players with
toggleable settings and multiple power tiers.

![Paper](https://img.shields.io/badge/Paper-1.21.4-green?logo=paper&logoColor=white)
[![MIT License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
![Vault](https://img.shields.io/badge/Vault-Compatible-blue?logo=bitcoin&logoColor=white)
[![Latest Release](https://img.shields.io/github/v/release/Chalwk/Paper-VacuLoot?sort=semver)](https://github.com/Chalwk/Paper-VacuLoot/releases/latest)

## Features

- **Toggleable Magnet**: Enable/disable item attraction with a simple command
- **Multiple Power Tiers**: 4 configurable tiers with different ranges (Basic, Advanced, Ultimate, God)
- **World Restrictions**: Configure which worlds allow magnet functionality
- **Smart Item Attraction**: Smooth, natural movement respecting Minecraft physics
- **Economy Integration**: Optional toggle cost with Vault support
- **Experience Orb Attraction**: Optional experience orb collection
- **Item Blacklist**: Exclude specific items from attraction
- **Configurable Cooldown**: Prevent toggle spam with adjustable cooldown
- **Per-Player Settings**: Admins can manage magnet states for other players
- **Permission-Based Tiers**: Tie magnet power to player permissions

## Commands

### Basic Commands

- `/magnet` or `/mag` - Toggle your magnet on/off
- `/magnet toggle` - Toggle your magnet (same as above)
- `/magnet check` - Check your current magnet status and tier
- `/magnet check <player>` - Check another player's magnet status
- `/magnet help` - Show command help

### Admin Commands

- `/magnet <player>` - Toggle magnet for another player (requires `magnet.use.others`)
- `/magnet toggle <player>` - Toggle magnet for another player
- `/magnet tier <player> <tier>` - Set a player's magnet tier (requires `magnet.admin`)
- `/magnet reload` - Reload the plugin configuration (requires `magnet.admin`)

## Permissions

- `magnet.use` - Allows using the magnet command (default: op)
- `magnet.use.others` - Allows toggling magnet for other players (default: op)
- `magnet.admin` - Access to admin management commands (default: op)
- `magnet.tier.basic` - Access to basic magnet tier (default: true)
- `magnet.tier.advanced` - Access to advanced magnet tier (default: op)
- `magnet.tier.ultimate` - Access to ultimate magnet tier (default: op)
- `magnet.tier.god` - Access to god magnet tier (default: op)
- `magnet.*` - Wildcard permission for all VacuLoot permissions

## Magnet Tiers System

VacuLoot features 4 configurable tiers with increasing power:

### Basic Tier (Default)

- **Range**: 5 blocks
- **Speed**: Normal
- **Permission**: `magnet.tier.basic`

### Advanced Tier

- **Range**: 10 blocks
- **Speed**: 20% faster
- **Permission**: `magnet.tier.advanced`

### Ultimate Tier

- **Range**: 15 blocks
- **Speed**: 50% faster
- **Permission**: `magnet.tier.ultimate`

### God Tier

- **Range**: 25 blocks
- **Speed**: 100% faster (2x speed)
- **Permission**: `magnet.tier.god`

## Configuration

The plugin generates a `config.yml` file with comprehensive options:

### General Settings

```yaml
# Default tier for players without specific tier permissions
default_tier: "basic"

# Magnet settings
magnet:
  # How often (in ticks) to check for nearby items (20 ticks = 1 second)
  interval: 5

# Toggle settings
toggle:
  # Cooldown between toggles in seconds
  cooldown: 5
```

### Attraction Settings

```yaml
# Attraction settings
attraction:
  # Whether to attract dropped items
  items: true
  # Whether to attract experience orbs
  experience: true
  # Base movement speed (higher = faster attraction)
  speed: 0.3
```

### Economy Settings (Requires Vault)

```yaml
economy:
  enabled: false
  # Cost to toggle magnet (per toggle)
  toggle_cost: 10.0
```

### World Restrictions

```yaml
# World restrictions (empty list = all worlds allowed)
worlds:
  allowed:
    - "world"
    - "world_nether"
    - "world_the_end"
```

### Item Blacklist

```yaml
# Item blacklist (items that won't be attracted)
blacklist:
  materials:
    - "BEDROCK"
    - "BARRIER"
    - "COMMAND_BLOCK"
```

### Magnet Tiers Configuration

```yaml
# Magnet tiers configuration
tiers:
  basic:
    range: 5.0
    speed_multiplier: 1.0
    permission: "magnet.tier.basic"
  advanced:
    range: 10.0
    speed_multiplier: 1.2
    permission: "magnet.tier.advanced"
  ultimate:
    range: 15.0
    speed_multiplier: 1.5
    permission: "magnet.tier.ultimate"
  god:
    range: 25.0
    speed_multiplier: 2.0
    permission: "magnet.tier.god"
```

### Messages

```yaml
messages:
  enabled: "&aMagnet &e{tier} &aenabled! &7(Range: &e{range}&7 blocks)"
  disabled: "&cMagnet disabled!"
  cooldown: "&cPlease wait {seconds} seconds before toggling magnet again!"
  insufficient_funds: "&cYou need ${amount} to toggle magnet!"
  toggled_for: "&aMagnet {state} for {player}!"
  toggled_by: "&aYour magnet was {state} by {sender}!"
  status: "&7[&bVacuLoot&7] {player}'s magnet: {status} &7(Tier: {tier}, Range: {range} blocks)"
  reloaded: "&aConfiguration reloaded!"
  player_not_found: "&cPlayer not found!"
  invalid_tier: "&cInvalid magnet tier! Available tiers: basic, advanced, ultimate, god"
  tier_set: "&aSet {player}'s magnet tier to {tier}"
  tier_changed: "&aYour magnet tier has been changed to {tier}"
  no_permission: "&cYou don't have permission to use this command!"
```

### Available Placeholders

- `{player}` - Player name
- `{state}` - Enabled/disabled state
- `{tier}` - Magnet tier name
- `{range}` - Magnet range in blocks
- `{status}` - ACTIVE/INACTIVE status
- `{seconds}` - Cooldown seconds remaining
- `{amount}` - Economy amount
- `{sender}` - Command sender name

## Usage Examples

### Basic Usage

```bash
# Toggle your magnet on/off
/magnet
> "Magnet basic enabled! (Range: 5.0 blocks)"
> "Magnet disabled!"

# Check your status
/magnet check
> "[VacuLoot] YourName's magnet: ACTIVE (Tier: basic, Range: 5.0 blocks)"
```

### Admin Usage

```bash
# Toggle magnet for another player
/magnet Notch
> "Magnet enabled for Notch!"

# Set a player's tier
/magnet tier Notch ultimate
> "Set Notch's magnet tier to ultimate"

# Check another player's status
/magnet check Notch
> "[VacuLoot] Notch's magnet: INACTIVE (Tier: ultimate, Range: 15.0 blocks)"

# Reload configuration
/magnet reload
> "Configuration reloaded!"
```

### Advanced Scenarios

```bash
# Mining session with magnet
/magnet
> "Magnet advanced enabled! (Range: 10.0 blocks)"
# All dropped ores now automatically float toward you

# Enderman farm with experience attraction
/magnet
> "Magnet ultimate enabled! (Range: 15.0 blocks)"
# Experience orbs and dropped items both attracted

# Economy-enabled server
/magnet
> "Magnet basic enabled! (Cost: $10 deducted)"
```

## Installation

### Prerequisites

- **Paper Server** 1.21.4 or higher
- **Java** 21 or higher
- **Optional**: [Vault](https://www.spigotmc.org/resources/vault.34315/) for economy features

### Installation Steps

1. Download the latest VacuLoot JAR from the [Releases](../../releases) section
2. Place the JAR file in your server's `plugins` folder
3. Restart your server
4. Configure the plugin in `plugins/VacuLoot/config.yml`
5. Set up appropriate permissions for your players
6. Enjoy automatic item collection!

### Building from Source

1. Clone the repository
2. Navigate to the project directory
3. Run the build command:

```bash
./gradlew build
```

4. The compiled JAR will be available in `build/libs/VacuLoot-1.0.0.jar`

## Customization

### Adding Custom Tiers

You can add additional magnet tiers by extending the `tiers` section in `config.yml`:

```yaml
tiers:
  # ... existing tiers ...
  cosmic:
    range: 50.0
    speed_multiplier: 3.0
    permission: "magnet.tier.cosmic"
```

### Custom World Configurations

Configure specific world behaviors:

```yaml
worlds:
  allowed:
    - "survival_world"
    - "resource_world"
  denied:
    - "pvp_arena"  # Explicitly deny specific worlds
```

### Advanced Blacklisting

Blacklist specific items using material names:

```yaml
blacklist:
  materials:
    - "DIAMOND_SWORD"  # Don't attract valuable weapons
    - "ELYTRA"         # Don't attract elytras
    - "SHULKER_BOX"    # Don't attract shulker boxes
  # You can also use partial names with wildcards
  patterns:
    - "*_BEDROCK"
    - "*_BARRIER"
```

## Economy Integration

When Vault is installed and economy is enabled:

- Players pay a configurable cost to toggle magnet
- Supports all major economy plugins (EssentialsX, CMI, etc.)
- Customizable cost per toggle

## Permission Setup Examples

### Basic Staff Setup

```yaml
group.staff:
  permissions:
    - magnet.use
    - magnet.tier.advanced
```

### Admin Setup

```yaml
group.admin:
  permissions:
    - magnet.use
    - magnet.use.others
    - magnet.admin
    - magnet.tier.god
    - magnet.*
```

### VIP Setup

```yaml
group.vip:
  permissions:
    - magnet.use
    - magnet.tier.ultimate
```

## License

Licensed under the [MIT License](LICENSE).