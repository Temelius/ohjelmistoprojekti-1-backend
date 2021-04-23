# Ohjelmistoprojekti 1 Backend
Haagahelian Ohjelmistoprojekti 1 kurssin palvelinpuoli

https://ohjelmistoprojekti-1-backend.herokuapp.com/
## Yleistä tietoa
- **master** eli main branch pitää sisällään sovelluksen testiversion ja ei ole tällä hetkellä samalla tasolla heroku version kanssa
- **heroku-version** branch on deployattuna [herokuun](https://ohjelmistoprojekti-1-backend.herokuapp.com/) PostgreSQL integraatiolla
## REST API
API pyynnöt ovat sallittuja osoitteeseen `https://ohjelmistoprojekti-1-backend.herokuapp.com/api/` ja kyseinen pyyntö palauttaa kaikki mahdolliset pyynnöt JSON muodossa.
### Get all quizzes
`/api/quiz/list`
```json
[
  {
    "quizId": 2,
    "quizName": "Kuulumiskysely",
    "question": [
      {
        "questionid": 3,
        "questionline": "Mitä kuuluu?",
        "answers": [
          {
            "answerid": 8,
            "answerline": "Hyvää",
            "userAnswers": []
          },
          {
            "answerid": 9,
            "answerline": "Huonoa",
            "userAnswers": []
          }
        ]
      }
    ]
  }
]
```
### Get Quiz by id
`/api/quiz/{id}`

```json
[
  {
    "quizId": 2,
    "quizName": "Kuulumiskysely",
    "question": [
      {
        "questionid": 3,
        "questionline": "Mitä kuuluu?",
        "answers": [
          {
            "answerid": 8,
            "answerline": "Hyvää",
            "userAnswers": []
          },
          {
            "answerid": 9,
            "answerline": "Huonoa",
            "userAnswers": []
          }
        ]
      }
    ]
  }
]
```

### Get all questions & question by id
`/api/questions`

`/api/question/{id}` palauttaa yhden questionin id:n perusteella.
```json
[
    {
	    "questionid": 3,
	    "questionline": "Mitä kuuluu?",
	    "answers": [
		    {
			    "answerid": 8,
			    "answerline": "Hyvää",
			    "userAnswers": []
			},
			{
				"answerid": 9,
				"answerline": "Huonoa",
				"userAnswers": []
			}
		]
	}...
]
```
### Get all answers & answer by id
`/api/answers`

`/api/answers/{id}` palauttaa yhden answerin id:n perusteella.
```json
[
    {
	    "answerid": 8,
	    "answerline": "Hyvää",
	    "userAnswers": []
	},
	{
		"answerid": 9,
		"answerline": "Huonoa",
		"userAnswers": []
	}
]
```
### Get all useranswers & useranswers by id

`/api/useranswers`

`/api/useranswers/{id}` palauttaa yhden useranswerin id:n perusteella.
```json
[
	{
		"userAnswerId": 17,
		"userAnswerLine": "Turuust"
	},
	{
		"userAnswerId": 18,
		"userAnswerLine": "Keuruult"
	}
]
```
