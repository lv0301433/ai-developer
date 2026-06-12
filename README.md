# AI Developer Platform

**Multi-language Auto Code Generation with AI-powered Testing and Self-Fixing**

## Features

✅ **Multi-Language Support**
- Java & Javac++
- Python, JavaScript, C++
- Go, Rust
- More languages coming soon

✅ **Automatic Code Generation**
- AI-powered code generation from requirements
- Support for multiple programming paradigms
- Template-based architecture

✅ **Smart Project Decomposition**
- Automatically breaks down large projects into manageable modules
- Maintains module dependencies
- Supports up to hundreds of micro-modules

✅ **Automated Testing**
- Runs tests on every generated module
- Comprehensive test coverage
- Real-time test result logging

✅ **AI Auto-Repair System**
- Automatically detects test failures
- Analyzes error logs
- Self-repairs code without human intervention
- Learns from repair patterns

✅ **One-Click Packaging**
- Automatic ZIP packaging
- Ready-to-deploy installations
- Includes all dependencies

✅ **REST API**
- Mobile-friendly endpoints
- Real-time status updates
- JSON response format

## Quick Start

```bash
git clone https://github.com/lv0301433/ai-developer.git
cd ai-developer
mvn clean install
mvn spring-boot:run
```

## API Endpoints

### Code Generation
- `POST /api/codegen/generate` - Generate code
- `POST /api/codegen/generate-modules` - Generate multiple modules
- `GET /api/codegen/supported-languages` - List supported languages

### Testing
- `POST /api/testing/run-tests` - Run automated tests
- `GET /api/testing/results` - Get test results

### Auto-Repair
- `POST /api/repair/auto-fix` - Auto-repair failed code

### Packaging
- `POST /api/packaging/create-package` - Package project
- `POST /api/packaging/extract-package` - Extract package

## Architecture

```
AI Developer
├── Core (Code Generation)
├── Testing (Automated Tests)
├── AI (Auto-Repair Engine)
├── Packaging (ZIP Utilities)
├── API (REST Controllers)
└── Orchestrator (Pipeline Coordination)
```

## License

MIT License - Free to use

## Support

For issues: https://github.com/lv0301433/ai-developer/issues
