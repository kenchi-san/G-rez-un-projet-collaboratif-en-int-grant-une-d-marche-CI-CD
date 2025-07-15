# 📦 CI/CD de BobApp – Documentation Technique

## 🎯 Objectif

Mettre en place une chaîne CI/CD complète et automatisée pour **BobApp**, avec :

- Exécution automatique des **tests back-end & front-end** ;
- Génération des **rapports de couverture** ;
- Analyse de la **qualité du code via SonarCloud** (avec KPI bloquants) ;
- **Build & push des images Docker** sur Docker Hub ;
- Déploiement contrôlé depuis la branche `main` uniquement.

---

## 📁 Arborescence recommandée du dépôt
```
bobapp/
├ .github/
│ └ workflows/
│ ├ pr-checks.yml # Tests + SonarCloud à chaque PR
│ └ release.yml # Build & push si tout est vert sur main
├ backend/ # Projet Spring Boot
│ └ pom.xml
├ frontend/ # Projet Angular
│ └ angular.json
├ sonar-project.properties # Config qualité SonarCloud
├ docs/
│ └ ci-cd.md # Ce document
└ README.md
```
yaml
Copier
Modifier

---

## 🔁 Étapes des Workflows GitHub Actions

### ✅ 1. `pr-checks.yml` – CI sur Pull Request

- Compile et teste le **backend** (`mvn verify`, JUnit + JaCoCo).
- Lance les **tests front-end** Angular (Karma + Jest + lcov).
- Envoie les rapports de couverture à **SonarCloud**.
- **Bloque la PR** si la qualité est insuffisante.

### 🚀 2. `release.yml` – CD sur `main`

- Exécute les mêmes contrôles qualité que `pr-checks.yml`.
- Vérifie le **passage du Quality Gate** SonarCloud.
- Si tout est OK :
    - Build les images Docker du front & back ;
    - Push sur Docker Hub (`:commit-sha` tag).

---

## 🧪 Outils & tests utilisés

| Composant       | Outils                   | Rapport généré         |
|------------------|--------------------------|-------------------------|
| Backend (Java)   | JUnit + JaCoCo (Maven)   | `jacoco.xml`            |
| Frontend (Angular)| Karma + Jest + lcov     | `lcov.info`             |
| Qualité Code     | SonarCloud               | Quality Gate bloquant   |

---

## 🔐 Secrets à définir (dans GitHub > Settings > Secrets)

| Nom du secret             | Description                          |
|---------------------------|--------------------------------------|
| `SONAR_TOKEN`             | Token d’accès SonarCloud              |
| `SONAR_PROJECT_KEY`       | Clé projet Sonar                      |
| `SONAR_ORG`               | Organisation SonarCloud               |
| `DOCKERHUB_USERNAME`      | Nom utilisateur Docker Hub            |
| `DOCKERHUB_TOKEN`         | Token Docker Hub (non le mot de passe) |

---

## ⚙️ Fichier `sonar-project.properties`

À placer à la **racine du repo** :

```properties
sonar.projectKey=BobApp
sonar.projectName=BobApp
sonar.organization=TON_ORG_SONARCLOUD
sonar.sources=backend/src/main/java,frontend/src/app
sonar.tests=backend/src/test/java,frontend/src
sonar.java.binaries=backend/target/classes
sonar.coverage.jacoco.xmlReportPaths=backend/target/site/jacoco/jacoco.xml
sonar.javascript.lcov.reportPaths=frontend/coverage/lcov.info
sonar.exclusions=**/*.spec.ts
sonar.qualitygate.wait=true
```
## 📊 KPI de Qualité retenus
```
Indicateur	Seuil	Pourquoi
Couverture du code nouveau	≥ 80 %	Garantir des tests pour chaque nouveauté
Nouveaux blocker issues	= 0	Éviter l’introduction de bugs critiques
Duplication sur code nouveau	< 3 %	Réduire les copiés-collés non maintenables
Complexité cognitive sur code nouveau	≤ 15	Préserver lisibilité & maintenabilité
```
## 📈 Exemple de métriques après 1ère exécution
```
Mesure	Back-end	Front-end
Couverture	22 %	18 %
Bugs Sonar	3 mineurs	5 mineurs
Code smells	120	80
Duplication	6 %	4 %
```
## 🛠 Plan d’action recommandé
```
Tests : Ajouter des tests unitaires et e2e ciblés sur :

L’API "suggestion de blague" ;

Le composant post-video.

Qualité Angular : Réduire les fichiers > 300 lignes.

Spring : Injecter les dépendances explicitement.

Front : Éviter le copier-coller des templates HTML.
```
## 🚨 Lecture des feedbacks utilisateurs (traduction technique)
```
Retour utilisateur	Interprétation	Action recommandée
"Impossible de poster une suggestion"	Front bug → boucle POST	

✅ Hot-fix + test e2e associé "Bug post vidéo toujours là" 
✅ GitHub Project + planning release "Je ne reçois plus rien"	Backend inactif (cron?)	
✅ Ajouter health-check + logs "J’ai supprimé ce site de mes favoris"	Frustration	
✅ Release notes + suivi bugs
```
## 📌 Étapes suivantes
```
Fork du dépôt original (en privé) ;

Ajout des secrets + workflows dans .github/workflows/ ;

Vérifier que tous les pipelines passent ;

Compléter ce fichier ci-cd.md si besoin (ex. versions, SHA, lien DockerHub…) ;

Envoyer le lien du dépôt à Bob ;

Préparer une démo : tableau de bord SonarCloud + logs GitHub Actions.
```
## ✅ Checklist
```
 PR tests & couverture automatisés

 Quality Gate bloquant

 Images Docker versionnées

 Secrets centralisés

 KPI lisibles & suivis

 Fichier ci-cd.md à jour
```