import React from 'react';

function CalculateScore(props) {
  const { Name, School, Total, Goal } = props;

  const averageScore = Total / 5; 

  const goalAchieved = averageScore >= Goal ? "Yes" : "No";

  return (
    <div className="score-card">
      <h2>Student Score Card</h2>
      <p><strong>Name:</strong> {Name}</p>
      <p><strong>School:</strong> {School}</p>
      <p><strong>Total Score:</strong> {Total}</p>
      <p><strong>Average Score:</strong> {averageScore.toFixed(2)}</p> 
      <p><strong>Goal:</strong> {Goal}</p>
      <p><strong>Goal Achieved:</strong> {goalAchieved}</p>
    </div>
  );
}

export default CalculateScore;