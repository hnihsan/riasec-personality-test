# RIASEC Personality Test — Project Analysis

## Project Overview

A full-stack web application that administers the **Holland Code (RIASEC) personality test** to users and recommends careers based on their results. The test scores users across six personality dimensions (Realistic, Investigative, Artistic, Social, Enterprising, Conventional) and returns matching professions.

---

## Architecture

```
riasec-personality-test/
├── backend/    # Spring Boot REST API (Java 17)
└── frontend/   # Next.js 13 App Router (TypeScript)
```

- **Backend** runs on port `9090`
- **Frontend** communicates with backend via `NEXT_PUBLIC_API_URL` environment variable
- **No Docker Compose or deployment config** exists — runs manually

---

## Backend

### Tech Stack

- **Java 17**
- **Spring Boot 3.0.6**
- **Spring Data JPA** (MySQL via `mysql-connector-j`)
- **Spring Data REST** (auto-exposes repositories as REST endpoints)
- **Drools 7.66.0.Final** (KIE rule engine for scoring and profession matching)
- **JavaFaker 1.0.2** (seed data generation utility — imported but not actively used in current seed code)
- **Maven** (build tool, uses `mvnw` wrapper)

### How to Run

```bash
cd backend
./mvnw spring-boot:run
```

### Database

- **MySQL** on `localhost:3306`, database name: `riasec`
- `spring.jpa.hibernate.ddl-auto=create` — **database is dropped and recreated on every startup**
- Seed data (`CommandLineRunner` beans) repopulates questions and professions on every startup
- Credentials are hardcoded in `application.properties` (not production-safe)

### Package Structure

```
riasec.backend
├── RiasecTestBackendApplication.java    # Main entry point; @ComponentScan for riasec.backend and riasec.backend.model.classes
├── SpringDataRestConfig.java            # CORS config: allows all origins/methods/headers
├── controller/
│   └── HollandCodeTestAttemptController.java  # Only custom REST controller
├── data/
│   ├── HollandCodeTestQuestionSeedData.java   # Seeds questions + creates 2 tests on startup
│   └── ProfessionsSeedData.java               # Seeds profession list on startup
├── model/
│   ├── classes/
│   │   ├── HollandCodeTest.java               # @Entity: test entity (title, description, version, questions)
│   │   ├── HollandCodeTestAttempt.java        # @Entity: stores one quiz attempt per user
│   │   ├── HollandCodeTestQuestion.java       # @Entity: a single yes/no question tagged with a PersonalityType
│   │   ├── Profession.java                    # @Entity: profession title + Holland code string (e.g. "RIE")
│   │   └── TestTaker.java                     # @Entity: user (firstName, lastName, email, gender)
│   ├── enums/
│   │   ├── PersonalityType.java               # REALISTIC, INVESTIGATIVE, ARTISTIC, SOCIAL, ENTERPRISING, CONVENTIONAL
│   │   └── Gender.java                        # FEMALE, MALE, NONBINARY, OTHER
│   └── interfaces/
│       ├── Test.java
│       ├── TestAttempt.java
│       └── TestQuestion.java
├── repository/
│   ├── HollandCodeTestAttemptRepository.java  # CrudRepository
│   ├── HollandCodeTestQuestionRepository.java # CrudRepository
│   ├── HollandCodeTestRepository.java         # CrudRepository
│   ├── ProfessionRepository.java              # CrudRepository
│   └── TestTakerRepository.java               # CrudRepository; custom: findByEmailAddress(String)
```

### Auto-exposed Spring Data REST Endpoints (via repositories)

- `GET /hollandCodeTests` — list all tests (HAL JSON with `_embedded`)
- `GET /hollandCodeTests/{id}` — single test with `testQuestions` embedded
- `GET /hollandCodeTestQuestions` — all questions
- `GET /professions` — all professions
- `GET /testTakers` — all test takers
- `GET /hollandCodeTestAttempts` — all attempts

### Custom Endpoint

- `POST /testAttempt` — submits quiz answers, runs Drools rules, saves attempt, returns results

#### `/testAttempt` Request Body

```json
{
  "testId": 1,
  "questionAnswer": { "101": true, "102": false, ... },
  "user": {
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane@example.com",
    "gender": "Female"
  }
}
```

#### `/testAttempt` Response Body

```json
{
  "hollandCode": "RIE",
  "exactProfessions": [ { "id": 1, "title": "...", "hollandCode": "RIE" } ],
  "similarProfessions": [ ... ]
}
```

### Drools Rule Engine

Rules are defined in `.drl` files under `src/main/resources/rules/`.
The Kie module is configured in `META-INF/kmodule.xml` with a `kbase` named `rules` and a `ksession` named `rulesKSession`.

**Rule files:**
| File | Salience | Purpose |
|------|----------|---------|
| `score.drl` | 10 | Increments per-personality scores for each `true` answer |
| `top-three-personalities.drl` | 5 | Determines top-3 personalities (version == 1, not used currently) |
| `top-3-personalities-v2.drl` | 5 | Determines top-3 personalities (version == 2, active) with tiebreaker priority: R > E > S > I > C > A |
| `matchProfessions.drl` | 2 | Matches professions to test taker's Holland code (exact + similar with ≥3 shared letters) |

**Rule execution flow:**

1. `score.drl` fires first (salience 10) → populates 6 individual scores on the `HollandCodeTestAttempt`
2. `top-3-personalities-v2.drl` fires second (salience 5) → sets `result` as a `List<String>` containing one 3-letter code (e.g., `["RIE"]`)
3. `matchProfessions.drl` fires last (salience 2) → populates `exactMatches` and `similarMatches` globals

**Important:** `kieSession.setGlobal("version", 2)` is hardcoded in the controller — only v2 rules are active.

### Seed Data

`HollandCodeTestQuestionSeedData` creates **~143 yes/no questions** (around 24 per personality type) and two `HollandCodeTest` entities:

1. **"Holland Code Test (Demo)"** — every 10th question from the full list (~14 questions)
2. **"Holland Code Test"** — full question set

`ProfessionsSeedData` seeds a large list of professions, each with title and 3-letter Holland code.

### Known Issues / Tech Debt

- `spring.jpa.hibernate.ddl-auto=create` drops all data on every restart — not suitable for production
- Credentials hardcoded in `application.properties` (username: `root`, password: `ashabulkahfi`)
- `KieSession` is created fresh per request but may not be closed (resource leak risk)
- No input validation on the `POST /testAttempt` endpoint — raw casting of request body
- CORS is fully open (`allowedOrigins("*")`) — not production-safe
- No authentication/authorization
- Only one custom controller exists; all other data access goes through Spring Data REST
- `HollandCodeTestAttempt` has both `setTest()` and `setHollandCodeTest()` inconsistency (the setter is `setTest` in the interface but the field is `hollandCodeTest`)

---

## Frontend

### Tech Stack

- **Next.js 13.4.1** (App Router)
- **TypeScript 5.0.4**
- **React 18.2.0**
- **Tailwind CSS 3.3.2**
- **SWR 2.1.5** (imported as dep but not actively used — data fetching is via plain `fetch`)
- `experimental.serverActions: true` enabled in `next.config.js`

### How to Run

```bash
cd frontend
npm install
npm run dev
```

### Environment Variable

`NEXT_PUBLIC_API_URL` must be set (e.g., `http://localhost:9090`) — no `.env` file is committed.

### Routing (App Router)

```
/                    → redirects to /tests
/tests               → TestList page (server component)
/tests/[id]          → PersonalDataForm page (client component — collects user info)
/tests/[id]/quiz     → Quiz page (server component wrapping Quiz client component)
/about               → Static Holland Code explanation page
```

### Component Map

| Component              | Type   | Purpose                                                                                                                                 |
| ---------------------- | ------ | --------------------------------------------------------------------------------------------------------------------------------------- |
| `TestList.tsx`         | Client | Renders list of `Test` components; has an info button linking to `/about`                                                               |
| `Test.tsx`             | Client | Single test card; random pastel background color generated via HSL on mount; checks `localStorage` for existing user email to skip form |
| `PersonalDataForm.tsx` | Client | Collects firstName, lastName, email, gender; saves to `localStorage`; pushes to quiz route                                              |
| `Quiz.tsx`             | Client | Renders one question at a time with Yes/No radio buttons; accumulates answers in state; shows `Result` when done                        |
| `Result.tsx`           | Client | Calls `getResult()` on mount via `useEffect`; displays Holland code, exact professions, similar professions                             |

### Data Flow

1. User visits `/tests` → server fetches `GET /hollandCodeTests` → renders test list
2. User clicks a test:
   - If `localStorage.email` exists → skip form, go directly to quiz
   - Otherwise → fill `PersonalDataForm`, data saved to `localStorage`
3. Quiz page fetches questions via `GET /hollandCodeTests/{id}` (returns `testQuestions` array)
4. User answers all questions in `Quiz.tsx` (Yes/No, one at a time)
5. On completion, `Result.tsx` calls `POST /testAttempt` with question answers + user data from `localStorage`
6. Result displays Holland code, exact profession matches, similar profession matches

### TypeScript Types (`types/global.d.ts`)

```typescript
ITest; // { id, title, description, version, testQuestions[] }
IQuestion; // { id, text, personalityType }
IQuizProps; // { questions: IQuestion[], testId: number }
IQuestionAnswers; // { questionAnswers: Map<IQuestion, boolean> }
IProfession; // { id, title, hollandCode }
IData; // { hollandCode, exactProfessions, similarProfessions }
```

### Styling

- Global background: `linear-gradient(to bottom, hsl(30, 85%, 85%), hsl(330, 87%, 78%))` (warm orange → pink gradient)
- Primary text color: `#f8f8f8` (near-white)
- Accent/muted text: `#767171`
- Highlighted accent: `#ce43a7` (magenta/pink) used for test titles
- Card backgrounds: `bg-[#ffffff2e]` (translucent white)
- Radio buttons have custom CSS using `:after` pseudo-element; selected = `#ee6cc1`
- No dark mode support

### Known Issues / Tech Debt

- User data stored in `localStorage` — no session management or auth
- `SWR` is listed as a dependency but not used
- `Quiz.tsx` uses radio buttons but doesn't reset selection between questions — previous selection visually persists
- `Result.tsx` uses `'use client'` implicitly (it uses hooks) but the file has no directive — it's imported in a server component
- No loading state while fetching questions on the quiz page
- No error boundaries; errors silently show "Error fetching data" text
- `any` types used in several places (`Test.tsx`, `Quiz.tsx`, `Result.tsx`)
- `PersonalDataForm.tsx` uses `e: any` for event handlers
- No `.env.example` file documenting required environment variables

---

## Key Domain Concepts

### Holland Code (RIASEC)

A 3-letter personality code derived by sorting the 6 personality type scores descending. Tiebreaks use priority: **R > E > S > I > C > A**. The top 3 letters form the person's code (e.g., `RIE`, `SAE`).

### Profession Matching

- **Exact match**: profession's Holland code equals user's code exactly
- **Similar match**: profession's Holland code shares ≥ 3 characters with user's code (order-independent)

### Personality Types

| Short | Full Name     | Nickname   |
| ----- | ------------- | ---------- |
| R     | Realistic     | Doers      |
| I     | Investigative | Thinkers   |
| A     | Artistic      | Creators   |
| S     | Social        | Helpers    |
| E     | Enterprising  | Persuaders |
| C     | Conventional  | Organizers |

---

## Development Notes for Future Agents

### When modifying backend rules:

- Rules live in `backend/src/main/resources/rules/*.drl`
- The active version is controlled by `kieSession.setGlobal("version", 2)` in `HollandCodeTestAttemptController`
- Salience order is critical: score rules (10) → top-3 rules (5) → profession matching rules (2)
- `matchProfessions.drl` reads `$testAttempt.getResult()` which is set by top-3 rules — order dependency

### When modifying models:

- `HollandCodeTestAttempt` stores scores as individual Integer fields (not a map) — each personality type has its own column
- The `result` field is `List<String>` containing the Holland code string (e.g., `["RIE"]`) — it's a list for historical reasons even though only one element is used
- `Profession.hollandCode` is a plain `String` like `"RIE"` — not an enum

### When modifying the API response:

- The `POST /testAttempt` response is built manually in the controller and returned as `Map<String, List<?>>`
- Response keys: `hollandCode` (actually comes from `testAttempt.getResult()`) — check actual controller return structure

### When modifying the frontend Quiz flow:

- Answers accumulate in a `Map<IQuestion, boolean>` inside `IQuestionAnswers` objects
- The full answers array is passed to `getResult()` which flattens it to `Record<number, boolean>` before POST
- User info is always read from `localStorage` at submission time (not from React state)

### When adding new question types or tests:

- Add questions in `HollandCodeTestQuestionSeedData.java`
- Database is recreated on every startup (`ddl-auto=create`) so changes take effect after restart
- To make persistent schema changes, change `ddl-auto` to `update` or `validate`

### When adding new personality types or changing scoring:

- `PersonalityType` enum must be updated
- `HollandCodeTestAttempt` needs new score fields
- `score.drl` needs a new scoring rule
- `top-3-personalities-v2.drl` needs updating for priority order
- Frontend `IQuestion.personalityType` is a plain string — no impact there

### Security concerns to be aware of:

- No auth on any endpoint
- CORS is `*` for all origins
- DB password in plaintext in `application.properties`
- `localStorage` used for user data (XSS risk)
- Unchecked casts in the controller from request body map

---

## Commands Reference

### Start Backend

```bash
cd backend && ./mvnw spring-boot:run
# Server runs at http://localhost:9090
```

### Start Frontend

```bash
cd frontend && npm run dev
# App runs at http://localhost:3000
# Requires: NEXT_PUBLIC_API_URL=http://localhost:9090
```

### Build Frontend

```bash
cd frontend && npm run build
```

### Lint Frontend

```bash
cd frontend && npm run lint
```
