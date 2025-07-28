import React from 'react';
import CohortDetails from './components/CohortDetails';
import './App.css';

function App() {
  const cohorts = [
    { id: 1, name: 'Web Dev Cohort A', status: 'ongoing', duration: '6 months', students: 45 },
    { id: 2, name: 'Data Science Cohort B', status: 'completed', duration: '8 months', students: 30 },
    { id: 3, name: 'Mobile App Cohort C', status: 'ongoing', duration: '5 months', students: 50 },
    { id: 4, name: 'Cloud Eng Cohort D', status: 'upcoming', duration: '7 months', students: 25 },
    { id: 5, name: 'AI/ML Cohort E', status: 'ongoing', duration: '9 months', students: 35 },
  ];

  return (
    <div className="App">
      <header style={{ textAlign: 'center', padding: '20px', backgroundColor: '#f0f0f0', borderBottom: '1px solid #ddd', marginBottom: '20px' }}>
        <h1>My Academy Dashboard</h1>
      </header>
      <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center', gap: '20px', padding: '10px' }}>
        {cohorts.map(cohort => (
          <CohortDetails key={cohort.id} cohort={cohort} />
        ))}
      </div>
    </div>
  );
}

export default App;