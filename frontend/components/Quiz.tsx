'use client';

import { useState } from 'react';
import Result from './Result';
import { LocaleProvider } from '../context/LocaleContext';
import LanguageSwitcher from './LanguageSwitcher';
import { useTranslation } from '../hooks/useTranslation';

function QuizContent(props: IQuizProps) {
  const { t } = useTranslation();
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [answers, setAnswers] = useState<Array<IQuestionAnswers>>([]);
  const currentQuestion = props.questions
    ? props.questions[currentQuestionIndex]
    : null;
  const [showResult, setShowResult] = useState(false);

  const handleAnswerClick = (answer: boolean) => {
    if (!currentQuestion) {
      return;
    }
    setAnswers((prevAnswers) => {
      const updatedAnswers = [...prevAnswers];
      const answeredQuestion: IQuestionAnswers = {
        questionAnswers: new Map([[currentQuestion, answer]]),
      };
      updatedAnswers[currentQuestionIndex] = answeredQuestion;
      return updatedAnswers;
    });

    const nextQuestionIndex = currentQuestionIndex + 1;

    if (nextQuestionIndex < props.questions?.length) {
      setCurrentQuestionIndex(nextQuestionIndex);
    } else {
      setShowResult(true);
    }
  };

  return (
    <>
      {!showResult && (
        <div className="max-w-2xl mx-auto ">
          <div className="flex items-center justify-between pt-6 mb-24">
            <h1 className="text-center font-bold text-[#FEFAE0] text-5xl flex-1">
              {t('quiz_title')}
            </h1>
            <LanguageSwitcher />
          </div>
          <div className="flex flex-col border-solid border-2 rounded-md items-center">
            <h4 className="mt-10 text-xl text-[#FEFAE0]/70">
              {t('question_counter', {
                current: currentQuestionIndex + 1,
                total: props.questions?.length,
              })}
            </h4>
            <div className="mt-8 text-2xl text-[#FEFAE0]">
              {currentQuestion
                ? t(currentQuestion.questionKey) || currentQuestion.text
                : null}
            </div>
            <div className="flex py-6 m-10 space-x-8 cursor-pointer">
              <div className="flex items-center">
                <input
                  type="radio"
                  className="w-6 h-6"
                  name="answer"
                  key={`yes-${currentQuestionIndex}`}
                  onClick={() => handleAnswerClick(true)}
                />
                <label className="text-[#FEFAE0] ml-2">{t('yes')}</label>
              </div>
              <div className="flex items-center">
                <input
                  type="radio"
                  className="w-6 h-6"
                  name="answer"
                  key={`no-${currentQuestionIndex}`}
                  onClick={() => handleAnswerClick(false)}
                />
                <label className="text-[#FEFAE0] ml-2">{t('no')}</label>
              </div>
            </div>
          </div>
        </div>
      )}

      {showResult && <Result testId={props.testId} answers={answers} />}
    </>
  );
}

export default function Quiz(props: IQuizProps) {
  return (
    <LocaleProvider>
      <QuizContent {...props} />
    </LocaleProvider>
  );
}
