import React from 'react';
import styles from './CohortDetails.module.css';

const CohortDetails = ({ cohort }) => {
  const h3Style = {
    color: cohort.status === 'ongoing' ? 'green' : 'blue',
    marginBottom: '10px'
  };

  return (
    <div className={styles.box}>
      <h3 style={h3Style}>{cohort.name}</h3>
      <dl>
        <dt>Status:</dt>
        <dd>{cohort.status}</dd>
        <dt>Duration:</dt>
        <dd>{cohort.duration}</dd>
        <dt>Students:</dt>
        <dd>{cohort.students}</dd>
      </dl>
    </div>
  );
};

export default CohortDetails;