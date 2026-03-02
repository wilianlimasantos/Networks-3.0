# 📥 Installation & Setup Guide

Complete guide for installing and configuring NetworksFolia on your Minecraft server.

---

## ⚡ Quick Install (5 minutes)

### 1. Prerequisites Check
Before installing, ensure you have:
```bash
# Check Java version (must be 21+)
java -version

# Expected output: openjdk version "21.X.X"
```

- ✅ Server running **Paper 1.21.4+** or **Folia**
- ✅ **Slimefun4** plugin installed (RC-37+)
- ✅ **SefiLib** plugin installed (0.2.6+)
- ✅ Java 21 or higher

### 2. Download JAR
Get the latest release from one of these sources:

**Option A: GitHub Releases**
```
https://github.com/Sefiraat/Networks-Folia/releases/latest
```

**Option B: JitPack Build**
```
https://jitpack.io/#Sefiraat/Networks-Folia
```

**Option C: Build from Source**
```bash
git clone https://github.com/Sefiraat/Networks-Folia.git
cd Networks-Folia
mvn clean package
# JAR in: target/Networks-vX.X.X.jar
```

### 3. Install Plugin
```bash
# Copy JAR to plugins folder
cp Networks-v2.0.0.jar /path/to/server/plugins/

# Restart server
./server.sh restart
# or
./start.bat
```

### 4. Verify Installation
```bash
# In-game, type:
/networks info

# Should show: "Networks v2.0.0 - Folia Ready"
```

✅ **Done!** Plugin is ready to use.

---

## 🔧 Full Setup Guide

### Step 1: Server Requirements

#### Minecraft Server Version
| Version | Supported | Recommended |
|---------|-----------|-------------|
| 1.21.4 | ✅ Full | ✅ BEST |
| 1.21.3 | ✅ Full | ✅ Good |
| 1.21.2 | ✅ Full | ✅ Good |
| 1.21.1 | ✅ Full | ✅ Good |
| 1.21 | ✅ Full | ✅ Good |
| 1.20.x | ❌ No | Not supported |
| 1.19.x | ❌ No | Not supported |

#### Server Software
```bash
# Install Paper (Recommended for standard servers)
wget https://launcher.papermc.io/api/v1/latest/builds/1.21/download

# OR Install Folia (Recommended for large networks)
wget https://ci.papermc.io/job/Folia/lastSuccessfulBuild/artifact/build/libs/folia*.jar
```

#### Java Requirements
```bash
# Install Java 21
# Ubuntu/Debian:
sudo apt update
sudo apt install openjdk-21-jre-headless

# CentOS/RHEL:
sudo yum install java-21-openjdk

# Windows: Download from https://adoptium.net/
```

#### RAM Requirements
| Server Size | Min RAM | Recommended |
|-------------|---------|------------|
| Small (10 players) | 2GB | 4GB |
| Medium (50 players) | 4GB | 8GB |
| Large (100+ players) | 8GB | 16GB |
| Enterprise | 16GB+ | 32GB+ |

### Step 2: Install Dependencies

Networks requires these plugins:

#### Slimefun4 (Required)
```bash
# Download Slimefun4 RC-37+
# From: https://github.com/Slimefun/Slimefun4/releases

# Place in plugins/
cp Slimefun4-RC-37.jar plugins/
```

#### SefiLib (Required)
```bash
# Download SefiLib 0.2.6+
# From: https://github.com/Sefiraat/SefiLib/releases

# Place in plugins/
cp SefiLib-0.2.6.jar plugins/
```

#### Optional Plugins (for integrations)

**Infinity Expansion** - Advanced storage
```bash
wget https://github.com/mooy1/InfinityExpansion/releases/download/latest/InfinityExpansion.jar
mv InfinityExpansion.jar plugins/
```

**Netheopoiesis** - Plant features
```bash
wget https://github.com/Sefiraat/Netheopoiesis/releases/download/latest/Netheopoiesis.jar
mv Netheopoiesis.jar plugins/
```

**SlimeHUD** - HUD display
```bash
wget https://github.com/schntgaispock/SlimeHUD/releases/download/latest/SlimeHUD.jar
mv SlimeHUD.jar plugins/
```

**WildChests** - Chest integration
```bash
wget https://github.com/BGSoftware/WildChests/releases/download/latest/WildChests.jar
mv WildChests.jar plugins/
```

### Step 3: Extract & Configure

After first start, the plugin creates files:

```
plugins/
├── Networks/
│   ├── config.yml          # Main configuration
│   ├── messages/
│   │   ├── en.yml         # English messages
│   │   └── pt_BR.yml      # Portuguese messages
│   └── data/
│       └── networks.db    # Network data (auto-generated)
├── Slimefun/
│   ├── config.yml
│   └── items.yml
└── Networks-v2.0.0.jar
```

### Step 4: Configure config.yml

```yaml
# plugins/Networks/config.yml

# ========== BASIC SETTINGS ==========
auto-update: false

# ========== NETWORK SETTINGS ==========
network:
  # Maximum nodes per controller (scale: 10-5000)
  max-nodes: 5000
  
  # Show network visualization particles
  enable-particles: true
  
  # Force-load chunks for network operations
  force-load-chunks: false

# ========== PERFORMANCE TUNING ==========
performance:
  # Network update frequency (ticks, lower = faster but more CPU)
  network-tick-rate: 1
  
  # Maximum items to move per tick per exporter
  # Adjust for lag: reduce if lagging, increase if too slow
  max-items-per-transfer: 64
  
  # Enable async processing (EXPERIMENTAL - may cause issues)
  async-processing: false

# ========== INTEGRATIONS ==========
integrations:
  # Support for external plugins
  infinity-expansion: true
  netheopoiesis: true
  slime-hud: true
  wild-chests: true
  mcmmo: true

# ========== SECURITY & ANTI-DUPE ==========
security:
  # Enable 7-layer dupe prevention (ALWAYS keep true)
  enable-dupe-protection: true
  
  # Network corruption cleanup interval (ticks)
  # Lower = more frequent checks, higher CPU
  cleanup-interval: 20
  
  # Protection strictness level
  # Options: basic, advanced, ultimate
  # ultimate = maximum security, slight performance hit
  protection-level: ultimate

# ========== LOGGING ==========
logging:
  # Enable debug logging
  debug: false
  
  # Log network events
  log-events: false
  
  # Log all item transfers (VERY verbose)
  log-transfers: false
```

### Step 5: Restart Server

```bash
# Graceful restart
/say Server restarting in 10 seconds...
/reload confirm

# Or full restart
./stop.sh
./start.sh
```

### Step 6: Verify Setup

```bash
# Check plugin loaded
/plugins

# Should show: Networks v2.0.0

# Check network status
/networks info

# Should show: "Networks ready: 0/5000 max"
```

---

## 🎯 First Steps After Install

### Create Your First Network

**1. Gather Materials** (via Slimefun)
- 1x Network Controller
- 2-5x Network Bridges
- 1x Quantum Storage (or Cell)
- 1x Network Grid

**2. Place Controller**
```
Rightclick with Network Controller block
Select flat ground area
```

**3. Extend Network**
```
Place Network Bridges in a line from controller
Each bridge extends network 1 block
```

**4. Add Storage**
```
Place Quantum Storage or Cells on network edges
Auto-connects to network (blue borders = connected)
```

**5. Add Access Point**
```
Place Network Grid on network
Rightclick to open item browser
```

✅ **Success!** You now have a working network!

---

## ⚙️ Advanced Configuration

### Performance Tuning

**For Low-Spec Servers**
```yaml
performance:
  network-tick-rate: 2        # Update every 2 ticks
  max-items-per-transfer: 32  # Reduce item flow
```

**For High-Performance Servers**
```yaml
performance:
  network-tick-rate: 1        # Maximum speed
  max-items-per-transfer: 128 # Maximum throughput
```

**For Very Large Networks (1000+ blocks)**
```yaml
performance:
  network-tick-rate: 1
  max-items-per-transfer: 64
  async-processing: false  # Keep false for safety
network:
  force-load-chunks: true   # Prevent unloading
```

### Folia-Specific Settings

Networks auto-detects Folia and adjusts:

```yaml
# Auto-detected, no manual config needed
# Networks uses RegionScheduler automatically
# Just ensure cleanup-interval is reasonable:
security:
  cleanup-interval: 20  # 1 second between checks
```

### Multi-World Setup

**Create separate networks per world:**

```yaml
# In each world's config
network:
  max-nodes: 5000       # Can be different per world
```

**Enable cross-world support (future version):**
```yaml
# Coming in v2.1.0
networking:
  cross-world: false    # Not yet available
  cross-dimension: false
```

---

## 🔐 Security Best Practices

### Database Backup
```bash
# Backup network data regularly
cp plugins/Networks/data/networks.db backups/networks-$(date +%Y%m%d).db

# Add to cron for daily backups:
0 3 * * * cp /path/to/plugins/Networks/data/networks.db /backups/networks-$(date +\%Y\%m\%d).db
```

### Permissions Setup

**Give players access:**
```yaml
# In permissions.yml or LuckPerms

# Let players use networks
groups:
  default:
    permissions:
      - networks.use      # Required for basic usage
      - networks.craft    # For crafting grids

# Admins get everything
groups:
  admin:
    permissions:
      - networks.admin    # Admin commands
      - networks.use
      - networks.craft
```

**LuckPerms Commands:**
```bash
# Give player permission
/lp user <player> permission set networks.use true

# Give group permission
/lp group default permission set networks.use true

# For admins
/lp group admin permission set networks.admin true
```

### Prevent Exploitation

**Settings to prevent dupes:**
```yaml
security:
  enable-dupe-protection: true    # Always TRUE
  cleanup-interval: 20             # Don't increase
  protection-level: ultimate       # Set to ultimate
```

**Monitor suspicious activity:**
```bash
# Enable debug logging to catch exploits
# In config.yml:
logging:
  debug: true
  log-events: true

# Check logs for repeated cleanup messages
tail -f logs/latest.log | grep "Network cleanup"
```

---

## 🐛 Troubleshooting

### Common Issues

#### Network not syncing
**Symptoms:** Items don't appear, slowness
**Solution:**
```bash
# 1. Reload plugin
/reload confirm

# 2. Check for corrupted networks
# (Should auto-cleanup, check logs)
tail -f logs/latest.log | grep "Network"

# 3. Rebuild network
# Break and replace grid
```

#### "This network already has a controller" error
**Cause:** Duplicate controller detected
**Solution:**
```bash
# 1. Find and remove the extra controller
# 2. Wait for auto-cleanup (20 ticks = 1 second)
# 3. Try placing again
```

#### Permission denied errors
**Cause:** Player lacks `networks.use` permission
**Solution:**
```bash
# Add permission to player
/lp user <player> permission set networks.use true

# Verify
/lp user <player> permission check networks.use
```

#### Server crashes on startup
**Cause:** Java version too old
**Solution:**
```bash
# Check Java version
java -version

# Must be 21+
# Update Java and restart
```

#### Folia "Unsupported Operation" warnings
**Cause:** Running on Paper with Folia detection
**Solution:** This is normal, not an error. To fix:
```bash
# Switch to actual Folia or delete Folia detection code
# (Already handled in v2.0.0, ignore if you see log message)
```

#### Performance degradation after long uptime
**Cause:** Network cache growing
**Solution:**
```yaml
# Increase cleanup frequency
security:
  cleanup-interval: 10  # Check every 0.5 seconds

# Or reduce max transfer size
performance:
  max-items-per-transfer: 32
```

### Getting Help

**Check these first:**
1. **Documentation:** https://sefiraat.dev
2. **GitHub Issues:** Search for similar problems
3. **FAQ:** Common questions section
4. **Logs:** Check latest.log for errors

**Report Issues:**
```bash
# Include:
# 1. Full error message
# 2. Server software and version
# 3. Java version
# 4. Plugin list (/plugins)
# 5. Reproduction steps
```

---

## 📊 Resource Usage

### Expected Consumption

| Network Size | RAM | CPU | Disk |
|--------------|-----|-----|------|
| 100 blocks | 5-10 MB | <1% | 1 MB |
| 1,000 blocks | 35-45 MB | 1-2% | 10 MB |
| 5,000 blocks | 100-150 MB | 3-5% | 50 MB |
| 10,000+ blocks | 200+ MB | 5-10% | 100+ MB |

### RAM Allocation

**Recommended JVM arguments:**
```bash
# For small servers
-Xmx2G -Xms2G

# For medium servers
-Xmx4G -Xms4G

# For large servers
-Xmx8G -Xms8G

# In server startup script:
java -Xmx8G -Xms8G -jar server.jar nogui
```

---

## 🚀 Optimization Tips

### Best Practices
1. **Regular backups** - Backup data daily
2. **Monitor logs** - Watch for warnings
3. **Test updates** - Update to latest builds
4. **Disable unused features** - Reduce config footprint
5. **Plan networks** - Design before building

### Server Optimization
```properties
# server.properties
# Reduce lag overall
view-distance=8
simulation-distance=6
network-compression-threshold=256
```

### Slimefun Optimization
```yaml
# plugins/Slimefun/config.yml
# Tune Slimefun for performance
general:
  ticker-delay: 1  # Match Networks tick-rate
```

---

## 📝 Configuration Profiles

### Profile: Creative Server
```yaml
network:
  max-nodes: 10000  # Unlimited
performance:
  network-tick-rate: 1
  max-items-per-transfer: 256  # Very fast
security:
  cleanup-interval: 100  # Less frequent checks
```

### Profile: Survival Server
```yaml
network:
  max-nodes: 5000  # Default
performance:
  network-tick-rate: 1
  max-items-per-transfer: 64
security:
  cleanup-interval: 20  # Regular checks
```

### Profile: Competitive SMP
```yaml
network:
  max-nodes: 2000  # Smaller networks
performance:
  network-tick-rate: 1
  max-items-per-transfer: 32  # Limited throughput
security:
  cleanup-interval: 10  # Frequent checks
  protection-level: ultimate  # Maximum security
```

### Profile: High-Performance Folia
```yaml
network:
  max-nodes: 5000
  force-load-chunks: true
performance:
  network-tick-rate: 1
  max-items-per-transfer: 128
security:
  cleanup-interval: 20  # RegionScheduler handles it
```

---

## ✅ Verification Checklist

After installation, verify:

- [ ] Java 21+ installed
- [ ] Paper/Folia 1.21.4+ running
- [ ] Slimefun4 RC-37+ installed
- [ ] SefiLib 0.2.6+ installed
- [ ] Networks JAR in plugins/
- [ ] config.yml generated and reviewed
- [ ] `/networks info` works
- [ ] Can place Network Controller
- [ ] Network connects automatically
- [ ] Grid opens without errors
- [ ] No permission errors for players
- [ ] Logs show no errors

✅ **All checked?** You're ready to go!

---

## 📞 Support

- 📖 **Docs:** https://sefiraat.dev
- 🐛 **Issues:** https://github.com/Sefiraat/Networks-Folia/issues
- 💬 **Discussions:** https://github.com/Sefiraat/Networks-Folia/discussions
- 📧 **Email:** security issues only (no public disclosure)

---

**Last Updated:** March 1, 2026
**Guide Version:** 2.0.0

