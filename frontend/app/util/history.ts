export async function getTestHistory(
  email: string
): Promise<ITestAttemptSummary[]> {
  try {
    const res = await fetch(
      `${
        process.env.NEXT_PUBLIC_API_URL
      }/testAttempt/history?email=${encodeURIComponent(email)}`
    );
    if (!res.ok) return [];
    return (await res.json()) as ITestAttemptSummary[];
  } catch {
    return [];
  }
}
