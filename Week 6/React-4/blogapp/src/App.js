import React from 'react';
import './App.css'; 
import Posts from './Posts'; 

function App() {
  return (
    <div className="App">
      <header className="App-header" style={{ backgroundColor: '#282c34', padding: '20px', color: 'white', textAlign: 'center' }}>
        <h1>Blog Application</h1>
      </header>
      <main>
        <Posts /> 
      </main>
    </div>
  );
}

export default App;