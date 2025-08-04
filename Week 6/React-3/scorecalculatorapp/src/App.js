import React from 'react';

import './Stylesheets/mystyle.css';

import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="app-container">
      <h1>Student Score Calculator</h1>
      <CalculateScore
        Name="Alice Johnson"
        School="Springfield High"
        Total={450} 
        Goal={90}    
      />

      <CalculateScore
        Name="Bob Williams"
        School="Evergreen Academy"
        Total={380}
        Goal={75}
      />

      <CalculateScore
        Name="Charlie Brown"
        School="Liberty School"
        Total={290}
        Goal={60}
      />
    </div>
  );
}

export default App;