export type ConfigState = {
  dbHost: string;
  dbPort: string;
  dbPath: string;
  username: string;
  password: string;
  establishmentCode: string;
  environment: 'Homolog' | 'Develop';
  syncTime: string; 
};


export type ConfigStatus = 'Homolog' | 'Develop';