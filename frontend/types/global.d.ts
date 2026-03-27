export {};

declare global {
  interface ITest {
    id: number;
    title: string;
    description: string;
    version: number;
    testQuestions: IQuestion[];
  }

  interface ITestProps {
    test: ITest;
  }

  interface IQuizProps {
    questions: IQuestion[];
    testId: number;
  }

  interface TestListProps {
    tests: ITest[];
  }

  interface IQuestion {
    text: string;
    personalityType: string;
    id: number;
    questionKey: string;
  }

  interface IQuestionAnswers {
    questionAnswers: Map<IQuestion, boolean>;
  }
  interface IProfession {
    id: number;
    title: string;
    hollandCode: string;
  }

  interface IData {
    hollandCode: string;
    exactProfessions: IProfession[];
    similarProfessions: IProfession[];
  }

  interface ITestTaker {
    id: number;
    firstName: string;
    lastName: string;
    emailAddress: string;
    gender: string;
  }

  interface ITestAttemptSummary {
    id: number;
    date: string;
    hollandCode: string;
    testTitle: string;
    exactProfessions: IProfession[];
    similarProfessions: IProfession[];
  }
}
